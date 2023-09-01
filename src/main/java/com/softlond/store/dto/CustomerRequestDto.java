package com.softlond.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {

    private Integer cardId;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private String rol;
}
