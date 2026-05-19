package com.infinity.os.dto.authdto;

public record AuthTokens(
        String accessToken,
        String refreshToken
) {}

