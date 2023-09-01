package com.softlond.store.exceptions;

public class PasswordIncorrectException extends RuntimeException {
    public PasswordIncorrectException() {
        super("La contraseña es inválida.");
    }
}