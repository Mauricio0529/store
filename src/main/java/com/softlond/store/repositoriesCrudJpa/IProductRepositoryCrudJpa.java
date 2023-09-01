package com.softlond.store.repositoriesCrudJpa;

import com.softlond.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepositoryCrudJpa extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryName(String name);
}