package com.infinity.os.controller;

import com.infinity.os.dto.authdto.AuthResponseDTO;
import com.infinity.os.dto.authdto.AuthTokens;
import com.infinity.os.dto.authdto.LoginRequestDTO;
import com.infinity.os.dto.userdto.UserRequestDTO;
import com.infinity.os.exception.RefreshTokenNaoEncontradoException;
import com.infinity.os.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private static final String SAME_SITE = "Strict";
    private static final long COOKIE_MAX_AGE = 7L * 24 * 60 * 60;

    private ResponseCookie buildRefreshCookie(String token) {
        return ResponseCookie.from("refreshToken", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(COOKIE_MAX_AGE)
                .sameSite(SAME_SITE)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO request
    ) {
        AuthTokens token = authService.login(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, buildRefreshCookie(token.refreshToken()).toString())
                .body(new AuthResponseDTO(token.accessToken()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody @Valid UserRequestDTO request
    ) {
        authService.register(request);
        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(HttpServletRequest request) {

        String refreshToken = Arrays.stream(Optional.ofNullable(request.getCookies())
                        .orElse(new Cookie[0]))
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(RefreshTokenNaoEncontradoException::new);

        AuthTokens tokens = authService.refreshToken(refreshToken);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, buildRefreshCookie(tokens.refreshToken()).toString())
                .body(new AuthResponseDTO(tokens.accessToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        String refreshToken = Arrays.stream(
                        Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(c -> c.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(RefreshTokenNaoEncontradoException::new);

        authService.logout(refreshToken);

        ResponseCookie clearCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite(SAME_SITE)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, clearCookie.toString())
                .body("Logout realizado com sucesso");
    }
}
