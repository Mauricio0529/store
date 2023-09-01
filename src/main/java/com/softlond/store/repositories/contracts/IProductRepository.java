package com.softlond.store.repositories.contracts;

import com.softlond.store.dto.ProductDto;
import com.softlond.store.dto.ProductRequestDto;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    List<ProductDto> getAll();

    Optional<ProductDto> getById(Long productId);

    List<ProductDto> getByNameCategory(String nameCategory);

    ProductRequestDto save(ProductRequestDto productDto);

    void delete(Long productId);
}