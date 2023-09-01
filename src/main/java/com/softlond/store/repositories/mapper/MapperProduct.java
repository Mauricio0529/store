package com.softlond.store.repositories.mapper;

import com.softlond.store.dto.ProductDto;
import com.softlond.store.dto.ProductRequestDto;
import com.softlond.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperProduct {

    ProductRequestDto toDto(Product product);

    ProductDto toDtoResponse(Product product);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "purchaseDetails", ignore = true)
    Product toEntity(ProductRequestDto productDto);

    List<ProductRequestDto> toDtoList(List<Product> productList);

    List<ProductDto> toDtoListResponse(List<Product> productList);


}