package com.softlond.store.exceptions;

public class CustomerNotExistException extends RuntimeException {

    public CustomerNotExistException() {
        super("El usuario ingresado no existe.");
    }
}