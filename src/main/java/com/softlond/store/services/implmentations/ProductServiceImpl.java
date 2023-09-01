package com.softlond.store.services.implmentations;

import com.softlond.store.dto.ProductDto;
import com.softlond.store.dto.ProductRequestDto;
import com.softlond.store.repositories.contracts.IProductRepository;
import com.softlond.store.services.contracts.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository iProductRepository;

    @Override
    public List<ProductDto> getAll() {
        return iProductRepository.getAll();
    }

    @Override
    public Optional<ProductDto> getById(Long idProduct) {
        return iProductRepository.getById(idProduct);
    }

    @Override
    public List<ProductDto> getByCategoryName(String categoryName) {
        return iProductRepository.getByNameCategory(categoryName);
    }

    @Override
    public ProductRequestDto save(ProductRequestDto product) {
        return iProductRepository.save(product);
    }

    @Override
    public Optional<ProductRequestDto> update(ProductRequestDto product) {
        if (getById(product.getId()).isEmpty()) {
            return Optional.empty();
        }
        iProductRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public Boolean delete(Long idProduct) {
        if (getById(idProduct).isEmpty()) {
            return false;
        }
        iProductRepository.delete(idProduct);
        return true;
    }
}