package com.softlond.store.services.contracts;

import com.softlond.store.dto.ProductDto;
import com.softlond.store.dto.ProductRequestDto;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<ProductDto> getAll();

    Optional<ProductDto> getById(Long idProduct);

    List<ProductDto> getByCategoryName(String categoryName);

    ProductRequestDto save(ProductRequestDto product);

    Optional<ProductRequestDto> update(ProductRequestDto product);

    Boolean delete(Long idProduct);

}