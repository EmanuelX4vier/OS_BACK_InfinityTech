package com.infinity.os.exception;

public class UserOrPassException extends RuntimeException {
    public UserOrPassException() {
        super("Usuário ou senha incorretos.");
    }
}
