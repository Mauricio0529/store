package com.softlond.store.repositories.implmentations;

import com.softlond.store.dto.CategoryDto;
import com.softlond.store.entities.Categories;
import com.softlond.store.repositories.contracts.ICategoryRepository;
import com.softlond.store.repositories.mapper.MapperCategory;
import com.softlond.store.repositoriesCrudJpa.ICategoryRepositoryCrudJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    private final ICategoryRepositoryCrudJpa iCategoryRepositoryCrudJpa;
    private final MapperCategory mapperCategory;

    @Override
    public List<CategoryDto> getAll() {
         return mapperCategory.toDtoList(iCategoryRepositoryCrudJpa.findAll());
    }

    @Override
    public Optional<CategoryDto> getById(Long categoryId) {
        return iCategoryRepositoryCrudJpa.findById(categoryId).map(mapperCategory::toDto);
    }

    @Override
    public List<CategoryDto> getByName(String name) {
        List<Categories> categoriesList = iCategoryRepositoryCrudJpa.findByName(name);
        return mapperCategory.toDtoList(categoriesList);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Categories categoriesEntity = iCategoryRepositoryCrudJpa.save(mapperCategory.toEntity(categoryDto));
        return mapperCategory.toDto(categoriesEntity);
    }

    @Override
    public void delete(Long categoryId) {
        iCategoryRepositoryCrudJpa.deleteById(categoryId);
    }
}