package com.softlond.store.repositories.implmentations;

import com.softlond.store.dto.PurchaseDto;
import com.softlond.store.entities.Purchase;
import com.softlond.store.repositories.contracts.IPurchaseRepository;
import com.softlond.store.repositories.mapper.MapperPurchase;
import com.softlond.store.repositoriesCrudJpa.IPurchaseRepositoryCrudJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PurchaseRepositoryImpl implements IPurchaseRepository {

    private final IPurchaseRepositoryCrudJpa iPurchaseRepositoryCrudJpa;

    private final MapperPurchase mapperPurchase;

    @Override
    public List<PurchaseDto> getAll() {
        return mapperPurchase.toDtoList(iPurchaseRepositoryCrudJpa.findAll());
    }

    @Override
    public Optional<PurchaseDto> getById(Long purchaseId) {
        Optional<PurchaseDto> purchaseDto = iPurchaseRepositoryCrudJpa.findById(purchaseId).map(mapperPurchase::toDto);
        if (purchaseDto.isEmpty()) {
            return Optional.empty();
        }
        return purchaseDto;
    }

    @Override
    public List<PurchaseDto> getByCodePurchase(String codePurchase) {
        List<Purchase> purchaseEntity  = iPurchaseRepositoryCrudJpa.findAllByCodePurchase(codePurchase);
        if (purchaseEntity.isEmpty()) {
            return null;
        }
        return mapperPurchase.toDtoList(purchaseEntity);
    }

    @Override
    public List<PurchaseDto> getByCustomerCardId(Integer customerCardId) {
        List<PurchaseDto> purchase = mapperPurchase.toDtoList(iPurchaseRepositoryCrudJpa.findAllByCustomerCardId(customerCardId));
        if (purchase.isEmpty()) {
            return null;
        }
        return purchase;
    }

    @Override
    public List<PurchaseDto> getByDatePurchaseBetween(LocalDate dateInitial, LocalDate dateFinal) {
        return mapperPurchase.toDtoList(iPurchaseRepositoryCrudJpa.findByDatePurchaseBetween(dateInitial, dateFinal));
    }

    @Override
    public List<PurchaseDto> getByCustomerCardIdAndDatePurchaseBetween(Integer customerCardId, LocalDate dateInitial, LocalDate dateFinal) {

        return mapperPurchase.toDtoList(iPurchaseRepositoryCrudJpa.findByCustomerCardIdAndDatePurchaseBetween(customerCardId, dateInitial, dateFinal));
    }

    @Override
    public PurchaseDto save(PurchaseDto purchaseDto) {
        Purchase purchase = mapperPurchase.toEntity(purchaseDto);

        purchase.setDatePurchase(LocalDateTime.now());

        purchase.getPurchaseDetails().stream().forEach(purchaseDetails -> purchaseDetails.setPurchase(purchase));

        iPurchaseRepositoryCrudJpa.save(purchase);

        return mapperPurchase.toDto(purchase);
    }

    @Override
    public void delete(Long purchaseId) {
        iPurchaseRepositoryCrudJpa.deleteById(purchaseId);
    }
}
