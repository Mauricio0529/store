package com.softlond.store.repositories.contracts;

import com.softlond.store.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {

    List<CategoryDto> getAll();

    Optional<CategoryDto> getById(Long categoryId);

    List<CategoryDto> getByName(String name);

    CategoryDto save(CategoryDto categoryDto);

    void delete(Long categoryId);
}