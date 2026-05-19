package com.infinity.os.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Esse email já existe!");
    }
}
