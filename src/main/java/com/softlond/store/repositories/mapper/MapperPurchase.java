package com.softlond.store.repositories.mapper;

import com.softlond.store.dto.PurchaseDto;
import com.softlond.store.entities.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {MapperPurchaseDetails.class})
public interface MapperPurchase {

    PurchaseDto toDto(Purchase purchaseEntity);

    /*@Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "winnerGameDiscount", ignore = true)
    @Mapping(target = "quantityDiscount", ignore = true)
    PurchaseResponseDto toDtoResponse(PurchaseDto purchaseDto);
    */

    @Mapping(target = "customer", ignore = true)
    Purchase toEntity(PurchaseDto purchaseDto);

    List<PurchaseDto> toDtoList(List<Purchase> purchaseList);

}