package com.infinity.os.dto.userdto.auth;

import com.infinity.os.types.Functions;

public record LoginResponseDTO(
        Long id,
        String nome,
        Functions funcao
) {}
