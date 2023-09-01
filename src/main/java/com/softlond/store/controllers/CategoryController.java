package com.softlond.store.controllers;

import com.softlond.store.constants.CategoryApiConstants;
import com.softlond.store.dto.CategoryDto;
import com.softlond.store.services.contracts.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = CategoryApiConstants.CATEGORY_API_PREFIX)
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;

    @GetMapping(CategoryApiConstants.LIST)
    private ResponseEntity<List<CategoryDto>> getAll() {
        return new ResponseEntity<List<CategoryDto>>(iCategoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping(CategoryApiConstants.GET_ID)
    private ResponseEntity<CategoryDto> getByCategoryId(@RequestParam Long categoryId) {
        return ResponseEntity.of(iCategoryService.getById(categoryId));
    }

    @GetMapping(CategoryApiConstants.GET_NAME)
    private ResponseEntity<List<CategoryDto>> getByCategoryName(@RequestParam String name) {
        return new ResponseEntity<List<CategoryDto>>(iCategoryService.getByName(name), HttpStatus.OK);
    }

    @PostMapping(CategoryApiConstants.CREATE)
    private ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity(iCategoryService.save(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping(CategoryApiConstants.UPDATE)
    private ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.of(iCategoryService.update(categoryDto));
    }

    @DeleteMapping(CategoryApiConstants.DELETE)
    private ResponseEntity<Boolean> delete(@RequestParam Long idCategory) {
        return new ResponseEntity<Boolean>(iCategoryService.delete(idCategory) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}