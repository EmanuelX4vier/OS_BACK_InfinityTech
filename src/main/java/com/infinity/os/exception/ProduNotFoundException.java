package com.infinity.os.exception;

public class ProduNotFoundException extends RuntimeException {
    public ProduNotFoundException() {
        super("Produto não econtrado, verifique o código.");
    }
}
