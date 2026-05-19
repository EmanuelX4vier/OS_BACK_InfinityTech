package com.infinity.os.exception;

public class RefreshTokenExpiradoException extends RuntimeException {
    public RefreshTokenExpiradoException() {
        super("Refresh token expirado.");
    }
}
