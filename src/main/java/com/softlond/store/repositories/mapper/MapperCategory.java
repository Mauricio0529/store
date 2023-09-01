package com.softlond.store.repositories.mapper;

import com.softlond.store.dto.CategoryDto;
import com.softlond.store.entities.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperCategory {

    CategoryDto toDto(Categories categories);

    @Mapping(target = "products", ignore = true)
    Categories toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Categories> categoriesList);

}