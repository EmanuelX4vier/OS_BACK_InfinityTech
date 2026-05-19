package com.infinity.os.repository;

import com.infinity.os.entity.RefreshToken;
import com.infinity.os.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    Optional<RefreshToken> findByToken(String token);
    void deleteByExpiresAtBefore(Instant now);
    void deleteByUser(User user);
    //void deleteByRevokedTrue();
}
