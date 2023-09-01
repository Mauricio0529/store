package com.softlond.store.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto de login usuario
 */

@Getter
@Setter
public class AuthCustomerDto {

    private String email;

    private String password;
}