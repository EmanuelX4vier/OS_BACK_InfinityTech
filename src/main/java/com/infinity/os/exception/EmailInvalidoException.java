package com.infinity.os.exception;

public class EmailInvalidoException extends RuntimeException {
    public EmailInvalidoException() {
        super("Email incorreto");
    }
}
