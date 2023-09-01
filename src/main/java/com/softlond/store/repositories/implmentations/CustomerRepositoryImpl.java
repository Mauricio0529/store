package com.softlond.store.repositories.implmentations;

import com.softlond.store.dto.CustomerRequestDto;
import com.softlond.store.entities.Customer;
import com.softlond.store.repositories.contracts.ICustomerRepository;
import com.softlond.store.repositories.mapper.MapperCustomer;
import com.softlond.store.repositoriesCrudJpa.ICustomerRepositoryCrudJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements ICustomerRepository {

    private final ICustomerRepositoryCrudJpa iCustomerRepositoryCrudJpa;
    private final MapperCustomer mapperCustomer;

    @Override
    public List<CustomerRequestDto> getAll() {
        return mapperCustomer.toDtoList(iCustomerRepositoryCrudJpa.findAll());
    }

    @Override
    public Optional<CustomerRequestDto> getByCardId(Integer cardId) {
        return iCustomerRepositoryCrudJpa.findById(cardId).map(mapperCustomer::toDto);
    }

    @Override
    public List<CustomerRequestDto> getByEmail(String email) {
        return mapperCustomer.toDtoList(iCustomerRepositoryCrudJpa.findByEmail(email));
    }

    @Override
    public CustomerRequestDto save(CustomerRequestDto customerRequestDto) {
        Customer customer = iCustomerRepositoryCrudJpa.save(mapperCustomer.toEntity(customerRequestDto));
        return mapperCustomer.toDto(iCustomerRepositoryCrudJpa.save(customer));
    }

    @Override
    public void delete(Long cardId) {
        iCustomerRepositoryCrudJpa.deleteById(Long.valueOf(cardId).intValue());
    }
}