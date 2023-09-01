package com.softlond.store.services.contracts;

import com.softlond.store.dto.CustomerRequestDto;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<CustomerRequestDto> getAll();

    Optional<CustomerRequestDto> getByCardId(Integer cardId);

    List<CustomerRequestDto> getByEmail(String email);

    CustomerRequestDto save(CustomerRequestDto customerRequestDto);

    Optional<CustomerRequestDto> update(CustomerRequestDto customerRequestDto);

    Boolean delete(Long cardId);

}
