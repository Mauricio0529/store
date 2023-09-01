package com.softlond.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto del token
 */

@Getter
@Setter
@AllArgsConstructor
public class JwtResponseDto {

    private String jwt;

}