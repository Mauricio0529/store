package com.softlond.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Purchase Request
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PurchaseDto {

    private Long id;

    private String codePurchase;

    private Integer customerCardId;

    private Integer totalNumberOfProductsPurchase;

    private Double totalPrice;

    private Integer numberLotteryToDiscount;

    private LocalDateTime datePurchase;

    private Double quantityDiscountForPurchaseMonth;

    private String winnerGameDiscount;

    private Double quantityDiscountLottery;

    private List<PurchaseDetailsDto> purchaseDetails;
}