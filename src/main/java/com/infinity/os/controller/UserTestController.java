package com.infinity.os.controller;

import com.infinity.os.entity.User;
import com.infinity.os.types.Functions;
import com.infinity.os.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class UserTestController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/create-user")
    public String createUser() {

        User user = new User();
        user.setNome("admin");
        user.setSenha(passwordEncoder.encode("123456"));
        user.setFuncao(Functions.ADMIN);

        userRepository.save(user);

        return "Usuário criado com sucesso";
    }
}