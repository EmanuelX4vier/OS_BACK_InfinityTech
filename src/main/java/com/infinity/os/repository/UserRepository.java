package com.infinity.os.repository;

import com.infinity.os.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNome(String nome);
    Optional<User> findByEmail(String email);
}
