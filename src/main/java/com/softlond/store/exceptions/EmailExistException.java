package com.softlond.store.exceptions;

public class EmailExistException extends RuntimeException {

    public EmailExistException () {
        super("El email ya se encuentra registrado.");
    }
}