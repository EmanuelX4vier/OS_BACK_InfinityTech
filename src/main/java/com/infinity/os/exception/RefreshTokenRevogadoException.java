package com.infinity.os.exception;

public class RefreshTokenRevogadoException extends RuntimeException {
    public RefreshTokenRevogadoException() {
        super("Refresh token foi revogado.");
    }
}
