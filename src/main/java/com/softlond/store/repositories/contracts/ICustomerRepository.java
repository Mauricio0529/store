package com.softlond.store.repositories.contracts;

import com.softlond.store.dto.CustomerRequestDto;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {

    List<CustomerRequestDto> getAll();

    Optional<CustomerRequestDto> getByCardId(Integer cardId);

    List<CustomerRequestDto> getByEmail(String email);

    CustomerRequestDto save(CustomerRequestDto customerRequestDto);

    void delete(Long productId);
}
