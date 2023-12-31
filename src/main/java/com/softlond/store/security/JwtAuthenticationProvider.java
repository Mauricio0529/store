package com.softlond.store.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.softlond.store.dto.CustomerRequestDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Clase encargada de la creacion y validacion de jwt para el inicio de sesion de un Usuario
 */
@Component
public class JwtAuthenticationProvider {

    /**
     * Llave para cifrar el jwt
     */
    //@Value("${jwt.secret.key}")
    private String secretKey = "LLAVE_SECRETA";

    /**
     * Lista blanca con los jwt creados
     */
    private HashMap<String, CustomerRequestDto> listToken = new HashMap<>();


    /**
     * Crea un nuevo jwt en base al cliente recibido por parametro y lo agrega a la lista blanca
     * @param customerJwt Cliente a utilizar en la creacion del jwt
     * @return Jwt creado
     */
    public String createToken(CustomerRequestDto customerJwt) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hora en milisegundos

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String tokenCreated = JWT.create()
                .withClaim("cardId", customerJwt.getCardId())
                .withClaim("name", customerJwt.getName())
                .withClaim("lastName", customerJwt.getLastName())
                .withClaim("email", customerJwt.getEmail())
                .withClaim("rol", customerJwt.getRol())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);

        listToken.put(tokenCreated, customerJwt);
        return tokenCreated;
    }


    /**
     * Valida si el token es valido y retorna una sesión del usuario
     * @param token Token a validar
     * @return Sesion del usuario
     * @ throws CredentialsExpiredException Si el token ya expiró
     * @throws BadCredentialsException Si el token no existe en la lista blanca
     */
    public Authentication validateToken(String token) throws AuthenticationException {

        System.out.println("entre tambien aqui");
        System.out.println(token);

        //verifica el token como su firma y expiración, lanza una excepcion si algo falla
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);


        CustomerRequestDto exists = listToken.get(token);
        if (exists == null) {
            throw new BadCredentialsException("Usuario no registrado.");
        }

        HashSet<SimpleGrantedAuthority> rolesAndAuthorities = new HashSet<>();
        rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_"+exists.getRol())); //rol
        // rolesAndAuthorities.add(new SimpleGrantedAuthority("ELIMINAR_PRIVILEGE")); // permisos del rol

        return new UsernamePasswordAuthenticationToken(exists, token, rolesAndAuthorities);
    }

    public String deleteToken(String jwt) {

        if (!listToken.containsKey(jwt)) {
            return "No existe token";
        }

        listToken.remove(jwt);
        return "Sesión cerrada exitosamente";
    }
}