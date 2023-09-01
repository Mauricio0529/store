package com.softlond.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Purchase Response
 */

@Getter
@Setter
public class PurchaseResponseDto {

    private Long id;

    private String codePurchase;

    private Integer customerCardId;

    private Integer totalNumberOfProductsPurchase;

    private Double totalPrice;

    private LocalDateTime datePurchase;

    private String winnerGameDiscount;

    private Double quantityDiscount;

    private List<PurchaseDetailsDto> purchaseDetails;
}