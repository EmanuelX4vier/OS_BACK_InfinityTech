package com.infinity.os.dto.userdto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "O nome de usuário é obrigatório")
        String nome,

        @NotBlank(message = "A senha é obrigatória")
        String senha
) {}