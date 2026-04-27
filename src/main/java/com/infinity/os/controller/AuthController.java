package com.infinity.os.controller;

import com.infinity.os.dto.userdto.auth.LoginRequestDTO;
import com.infinity.os.dto.userdto.auth.LoginResponseDTO;
import com.infinity.os.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        // O Service valida e retorna o DTO de resposta se estiver tudo OK
        LoginResponseDTO response = userService.loginValidation(dto);
        return ResponseEntity.ok(response);
    }
}