package com.softlond.store.services.implmentations;

import com.softlond.store.dto.CategoryDto;
import com.softlond.store.repositories.contracts.ICategoryRepository;
import com.softlond.store.services.contracts.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository iCategoryRepository;

    @Override
    public List<CategoryDto> getAll() {
        return iCategoryRepository.getAll();
    }

    @Override
    public Optional<CategoryDto> getById(Long idCategory) {
        return iCategoryRepository.getById(idCategory);
    }

    @Override
    public List<CategoryDto> getByName(String name) {
        return iCategoryRepository.getByName(name);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return iCategoryRepository.save(categoryDto);
    }

    @Override
    @Transactional
    public Optional<CategoryDto> update(CategoryDto categoryDto) {
        if (getById(categoryDto.getId()).isEmpty()) {
            return Optional.empty();
        }
        iCategoryRepository.save(categoryDto);
        return Optional.of(categoryDto);
    }

    @Override
    public Boolean delete(Long idCategory) {
        if(getById(idCategory).isEmpty()) {
            return false;
        }

        iCategoryRepository.delete(idCategory);
        return true;
    }

}