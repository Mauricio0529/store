package com.softlond.store.services.contracts;

import com.softlond.store.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<CategoryDto> getAll();

    Optional<CategoryDto> getById(Long idCategory);

    List<CategoryDto> getByName(String name);

    CategoryDto save(CategoryDto categoryDto);

    Optional<CategoryDto> update(CategoryDto categoryDto);

    Boolean delete(Long idCategory);
}