package com.softlond.store.services.contracts;

import com.softlond.store.dto.AuthCustomerDto;
import com.softlond.store.dto.JwtResponseDto;

public interface IAuthService {

    JwtResponseDto signIn(AuthCustomerDto authCustomerDto);

    JwtResponseDto signOut(String jwt);

}