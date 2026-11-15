package com.infinity.os.exception;

public class RefreshTokenNotFound extends RuntimeException {
    public RefreshTokenNotFound() {
        super("Refresh token não encontrado.");
    }
}