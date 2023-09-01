package com.softlond.store.controllers;

import com.softlond.store.constants.AuthApiConstants;
import com.softlond.store.constants.CustomerApiConstants;
import com.softlond.store.dto.AuthCustomerDto;
import com.softlond.store.dto.CustomerRequestDto;
import com.softlond.store.dto.JwtResponseDto;
import com.softlond.store.services.contracts.IAuthService;
import com.softlond.store.services.contracts.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CLASE QUE GENERA EL TOKEN
 */

@RequiredArgsConstructor
@RestController
@RequestMapping(path = AuthApiConstants.AUTH_API_PREFIX)
public class AuthController {

    private final IAuthService authService;

    private final ICustomerService iCustomerService;

    /**
     * crear un nuevo usuario
     */
    @PostMapping(path = CustomerApiConstants.CREATE)
    public ResponseEntity<CustomerRequestDto> save(@RequestBody CustomerRequestDto customerDtoNew) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCustomerService.save(customerDtoNew));
    }

    /**
     * inicia sesion
     */
    @PostMapping(path = AuthApiConstants.SIGN_IN)
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody AuthCustomerDto authCustomerDto) {
        return ResponseEntity.ok(authService.signIn(authCustomerDto));
    }

    /**
     * cerrar sesion
     */
    @PostMapping(path = AuthApiConstants.SIGN_OUT)
    public ResponseEntity<JwtResponseDto> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String jwt) {
        return ResponseEntity.ok(authService.signOut(jwt));
    }
}