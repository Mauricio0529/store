package com.softlond.store.controllers;

import com.softlond.store.constants.ProductApiConstants;
import com.softlond.store.dto.ProductDto;
import com.softlond.store.dto.ProductRequestDto;
import com.softlond.store.services.contracts.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ProductApiConstants.PRODUCT_API_PREFIX)
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @GetMapping(ProductApiConstants.LIST)
    private ResponseEntity<List<ProductDto>> getAll() {
        return new ResponseEntity<List<ProductDto>>(iProductService.getAll(), HttpStatus.OK);
    }

    @GetMapping(ProductApiConstants.GET_ID)
    private ResponseEntity<ProductDto> getById(@RequestParam Long productId) {
        return ResponseEntity.of(iProductService.getById(productId));
    }

    @GetMapping(ProductApiConstants.GET_CATEGORY_BY_NAME)
    private ResponseEntity<List<ProductDto>> getAllByCategoryName(@RequestParam String categoryName) {
        return new ResponseEntity<List<ProductDto>>(iProductService.getByCategoryName(categoryName), HttpStatus.OK);
    }

    @PostMapping(ProductApiConstants.CREATE)
    private ResponseEntity<ProductRequestDto> save(@RequestBody ProductRequestDto product) {
        return new ResponseEntity(iProductService.save(product), HttpStatus.CREATED);
    }

    @PutMapping(ProductApiConstants.UPDATE)
    private ResponseEntity<ProductRequestDto> update(@RequestBody ProductRequestDto product) {
        return ResponseEntity.of(iProductService.update(product));
    }

    @DeleteMapping(ProductApiConstants.DELETE)
    private ResponseEntity<Boolean> update(@RequestParam Long idProduct) {
        return new ResponseEntity<Boolean>(iProductService.delete(idProduct) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}