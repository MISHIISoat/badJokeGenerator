package com.soat.badjokegenerator.usecase;

public class BadJokeForbiddenException extends Exception {
    public BadJokeForbiddenException(String message) {
        super(message);
    }
}
