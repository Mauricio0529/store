package com.softlond.store.repositoriesCrudJpa;

import com.softlond.store.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepositoryCrudJpa extends JpaRepository<Customer, Integer> {
    List<Customer> findByEmail(String email);
}