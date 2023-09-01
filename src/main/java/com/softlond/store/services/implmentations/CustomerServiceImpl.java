package com.softlond.store.services.implmentations;

import com.softlond.store.dto.CustomerRequestDto;
import com.softlond.store.exceptions.CardIdValidationException;
import com.softlond.store.exceptions.EmailExistException;
import com.softlond.store.repositories.contracts.ICustomerRepository;
import com.softlond.store.security.Roles;
import com.softlond.store.services.contracts.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository iCustomerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<CustomerRequestDto> getAll() {
        return iCustomerRepository.getAll();
    }

    @Override
    public Optional<CustomerRequestDto> getByCardId(Integer cardId) {
        Optional<CustomerRequestDto> customerRequestDto = iCustomerRepository.getByCardId(cardId);
        if(customerRequestDto.isEmpty()) {
            return Optional.empty();
        }
        return customerRequestDto;
    }

    @Override
    public List<CustomerRequestDto> getByEmail(String email) {
        return iCustomerRepository.getByEmail(email);
    }

    @Override
    public CustomerRequestDto save(CustomerRequestDto customerRequestDto) {

        // validar cedula repetida
        if(getByCardId(customerRequestDto.getCardId()).isPresent()) {
            throw new CardIdValidationException();
        }

        // validar email repetido
        if(getByEmail(customerRequestDto.getEmail()).size() != 0) {
            throw new EmailExistException();
        }

        customerRequestDto.setPassword(passwordEncoder.encode(customerRequestDto.getPassword()));
        customerRequestDto.setRol(Roles.CUSTOMER);
        return iCustomerRepository.save(customerRequestDto);
    }

    @Override
    public Optional<CustomerRequestDto> update(CustomerRequestDto customerRequestDto) {
        if (getByCardId(customerRequestDto.getCardId()).isEmpty()) {
            return Optional.empty();
        }
        iCustomerRepository.save(customerRequestDto);
        return Optional.of(customerRequestDto);
    }

    @Override
    public Boolean delete(Long cardId) {
        if(getByCardId(Long.valueOf(cardId).intValue()).isEmpty()) {
            return false;
        }

        iCustomerRepository.delete(cardId);
        return true;
    }
}