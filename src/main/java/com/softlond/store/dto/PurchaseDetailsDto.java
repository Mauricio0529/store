package com.softlond.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PurchaseDetailsDto {

    private Long purchaseId;

    private Long productId;

    private String nameProduct;

    private Double price; // BigDecimal

    private Integer quantityProduct;

    private Double totalPriceProducts;

}