package com.softlond.store.services.implmentations;

import com.softlond.store.dto.AuthCustomerDto;
import com.softlond.store.dto.CustomerRequestDto;
import com.softlond.store.dto.JwtResponseDto;
import com.softlond.store.exceptions.CustomerNotExistException;
import com.softlond.store.exceptions.PasswordIncorrectException;
import com.softlond.store.repositories.contracts.ICustomerRepository;
import com.softlond.store.security.JwtAuthenticationProvider;
import com.softlond.store.services.contracts.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado del logueo de un usuario
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final ICustomerRepository customerRepository;

    /**
     * Clase que administra los JWTs
     */
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * Clase que encripta contraseñas
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Inicia sesion.
     * Devuelve un dto con el jwt del usuario dadas unas credenciales
     * @param authCustomerDto Credenciales de acceso
     * @return Dto con el jwt del usuario si las credenciales son validas
     */
    public JwtResponseDto signIn(AuthCustomerDto authCustomerDto) {

        List<CustomerRequestDto> customer = customerRepository.getByEmail(authCustomerDto.getEmail());

        if (customer.isEmpty()) {
            throw new CustomerNotExistException();
        }

        /**
         * match para comparar la contraseña
         */
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        for (CustomerRequestDto customerDto: customer) {
            customerRequestDto.setCardId(customerDto.getCardId());
            customerRequestDto.setName(customerDto.getName());
            customerRequestDto.setLastName(customerDto.getLastName());
            customerRequestDto.setEmail(customerDto.getEmail());
            customerRequestDto.setPassword(customerDto.getPassword());
            customerRequestDto.setRol(customerDto.getRol());
        }

        if (!passwordEncoder.matches(authCustomerDto.getPassword(), customerRequestDto.getPassword())) {
            throw new PasswordIncorrectException();
        }

        return new JwtResponseDto(jwtAuthenticationProvider.createToken(customerRequestDto));
    }

    /**
     * Cierra la sesión eliminando de la lista blanca el token ingresado
     * @param token Token a eliminar
     * @return
     */
    public JwtResponseDto signOut(String token) {

        String[] authElements = token.split(" ");
        return new JwtResponseDto(jwtAuthenticationProvider.deleteToken(authElements[1]));
    }
}