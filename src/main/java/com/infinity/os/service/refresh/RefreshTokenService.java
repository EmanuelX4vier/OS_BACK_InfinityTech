package com.infinity.os.service.refresh;

import com.infinity.os.entity.RefreshToken;
import com.infinity.os.entity.User;
import com.infinity.os.exception.RefreshTokenExpiradoException;
import com.infinity.os.exception.RefreshTokenNaoEncontradoException;
import com.infinity.os.exception.RefreshTokenRevogadoException;
import com.infinity.os.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Value("${jwt.refresh-token-expiration-days:7}")
    private long refreshTokenDurationDays;

    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiresAt(Instant.now().plus(refreshTokenDurationDays, ChronoUnit.DAYS))
                .revoked(false)
                .build();

        return repository.save(refreshToken);
    }

    public RefreshToken validateToken(String token) {

        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(RefreshTokenNaoEncontradoException::new);

        if (refreshToken.isRevoked()) {
            throw new RefreshTokenRevogadoException();
        }

        if (refreshToken.getExpiresAt().isBefore(Instant.now())) {
            throw new RefreshTokenExpiradoException();
        }

        return refreshToken;
    }

    public void revokeToken(String token) {

        // CORRIGIDO: usa exceção customizada
        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(RefreshTokenNaoEncontradoException::new);

        refreshToken.setRevoked(true);
        repository.save(refreshToken);
    }

    @Transactional
    public void revokeAllByUser(User user) {
        repository.deleteByUser(user);
    }

    // CORRIGIDO: @Scheduled só funciona com @EnableScheduling na classe principal (OSApplication).
    @Scheduled(cron = "0 0 3 * * *") // todo dia às 3h da manhã
    @Transactional
    public void limparTokensExpirados() {
        repository.deleteByExpiresAtBefore(Instant.now());
    }
}
