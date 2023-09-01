package com.softlond.store.exceptions;

public class CardIdValidationException extends RuntimeException {

    public  CardIdValidationException() {
        super("La cedula ingresada ya se encuentra registrada");
    }
}