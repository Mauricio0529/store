package com.softlond.store.repositories.implmentations;

import com.softlond.store.dto.ProductDto;
import com.softlond.store.dto.ProductRequestDto;
import com.softlond.store.entities.Categories;
import com.softlond.store.entities.Product;
import com.softlond.store.repositories.contracts.IProductRepository;
import com.softlond.store.repositories.mapper.MapperProduct;
import com.softlond.store.repositoriesCrudJpa.ICategoryRepositoryCrudJpa;
import com.softlond.store.repositoriesCrudJpa.IProductRepositoryCrudJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements IProductRepository {


    private final IProductRepositoryCrudJpa iProductRepositoryCrudJpa;
    private final ICategoryRepositoryCrudJpa iCategoryRepositoryCrudJpa;

    private final MapperProduct mapperProduct;

    @Override
    public List<ProductDto> getAll() {
        List<Product> productList = iProductRepositoryCrudJpa.findAll();
        List<ProductDto> productDtoList = mapperProduct.toDtoListResponse(productList);

        productDtoList.stream().forEach(productDto -> {
            productList.stream()
                    .filter(productEntity -> productEntity.getId() == productDto.getId())
                    .forEach(product -> {
                        String categoryName = product.getCategory().getName();
                        productDto.setCategoryName(categoryName);
                    });
        });

        return productDtoList;
    }

    @Override
    public Optional<ProductDto> getById(Long productId) {

        Product productEntity = iProductRepositoryCrudJpa.findById(productId).get();
        Categories categoriesEntity = iCategoryRepositoryCrudJpa.findById(Long.valueOf(productEntity.getCategoryId())).get();
        ProductDto productDto = mapperProduct.toDtoResponse(productEntity);
        productDto.setCategoryName(categoriesEntity.getName());

        return Optional.of(productDto);

    }

    @Override
    public List<ProductDto> getByNameCategory(String nameCategory) {

        List<Product> productEntityList = iProductRepositoryCrudJpa.findAllByCategoryName(nameCategory);

        List<ProductDto> productDtoListDto = mapperProduct.toDtoListResponse(productEntityList);

        productDtoListDto.forEach(productDto -> {
            productEntityList.stream().filter(product -> product.getId() == productDto.getId())
                    .forEach(productEntity -> {
                        String category = productEntity.getCategory().getName();
                        productDto.setCategoryName(category);
                    });
        });

        return productDtoListDto;

    }

    @Override
    public ProductRequestDto save(ProductRequestDto productDto) {
        if (productDto.getActive() == null) {
            productDto.setActive(1);
        }

        if (productDto.getActive() != 0) {
            productDto.setActive(1);
        }

        Product productEntity = mapperProduct.toEntity(productDto);
        productEntity.setCreated_at(LocalDateTime.now());
        iProductRepositoryCrudJpa.save(productEntity);

        return mapperProduct.toDto(productEntity);
    }

    @Override
    public void delete(Long productId) {
      iProductRepositoryCrudJpa.deleteById(productId);
    }
}