package com.infinity.os.exception;

public class RefreshTokenNaoEncontradoException extends RuntimeException {
    public RefreshTokenNaoEncontradoException() {
        super("Refresh token não encontrado.");
    }
}