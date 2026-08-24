package com.infinity.os.service.auth;

import com.infinity.os.dto.authdto.AuthTokens;
import com.infinity.os.dto.authdto.LoginRequestDTO;
import com.infinity.os.dto.userdto.UserRequestDTO;
import com.infinity.os.entity.RefreshToken;
import com.infinity.os.entity.User;
import com.infinity.os.exception.EmailAlreadyExistsException;
import com.infinity.os.repository.UserRepository;
import com.infinity.os.security.JwtService;
import com.infinity.os.service.refresh.RefreshTokenResult;
import com.infinity.os.service.refresh.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    //Token
    public AuthTokens refreshToken(String refreshToken) {

        //Valida o refresh token (existência, expiração e revogação)
        RefreshToken token = refreshTokenService.validateToken(refreshToken);

        User user = token.getUser();

        //Gera novo acesso token
        String newAccessToken = jwtService.generateToken(user.getEmail().toLowerCase());

        refreshTokenService.revokeToken(refreshToken);
        RefreshTokenResult newRefreshTokenResult = refreshTokenService.createRefreshToken(user);

        return new AuthTokens(newAccessToken, newRefreshTokenResult.token());
    }

    //Login - Logout - Register
    public AuthTokens login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email().toLowerCase(),
                        request.senha()
                )
        );

        User user = userRepository.findByEmail(request.email().toLowerCase())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String accessToken = jwtService.generateToken(user.getEmail().toLowerCase());
        refreshTokenService.revokeAllByUser(user);
        RefreshTokenResult refreshTokenResult = refreshTokenService.createRefreshToken(user);

        return new AuthTokens(
                accessToken,
                refreshTokenResult.token()
        );
    }

    public void logout(String refreshToken) {

        refreshTokenService.revokeToken(refreshToken);
    }

    public void register(UserRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail().toLowerCase()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        User user = User.builder()
                .nome(request.getNome())
                .email(request.getEmail().toLowerCase())
                .senha(passwordEncoder.encode(request.getSenha()))
                .funcao(request.getFuncao())
                .build();

        userRepository.save(user);
    }
}