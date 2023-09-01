package com.softlond.store.repositories.mapper;

import com.softlond.store.dto.PurchaseDetailsDto;
import com.softlond.store.entities.PurchaseDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperPurchaseDetails {

    @Mapping(source = "productId", target = "purchaseDetailsPK.productId")
    @Mapping(target = "purchase", ignore = true)
    @Mapping(target = "product", ignore = true)
    PurchaseDetails toEntity(PurchaseDetailsDto purchaseDetailsDto);

}