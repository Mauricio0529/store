package com.softlond.store.repositories.mapper;

import com.softlond.store.dto.CustomerRequestDto;
import com.softlond.store.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperCustomer {

    CustomerRequestDto toDto(Customer customer);

    @Mapping(target = "purchaseList", ignore = true)
    Customer toEntity(CustomerRequestDto customerRequestDto);

    List<CustomerRequestDto> toDtoList(List<Customer> customerList);

}