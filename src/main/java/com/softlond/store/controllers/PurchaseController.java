package com.softlond.store.controllers;

import com.softlond.store.constants.PurchaseApiConstants;
import com.softlond.store.dto.PurchaseDto;
import com.softlond.store.services.contracts.IPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = PurchaseApiConstants.PURCHASE_API_PREFIX)
@RequiredArgsConstructor
public class PurchaseController {

    private final IPurchaseService iPurchaseService;

    @GetMapping(PurchaseApiConstants.LIST)
    private ResponseEntity<List<PurchaseDto>> getAll() {
        return new ResponseEntity<List<PurchaseDto>>(iPurchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping(PurchaseApiConstants.GET_ID)
    private ResponseEntity<PurchaseDto> getById(@RequestParam Long purchaseId) {
        return ResponseEntity.of(iPurchaseService.getById(purchaseId));
    }

    @GetMapping(PurchaseApiConstants.GET_CODE_PURCHASE)
    private ResponseEntity<List<PurchaseDto>> getByCodePurchase(@RequestParam String codePurchase) {
        return ResponseEntity.ok(iPurchaseService.getByCodePurchase(codePurchase));
    }

    @GetMapping(PurchaseApiConstants.GET_CUSTOMER_BY_CARD_ID)
    private ResponseEntity<List<PurchaseDto>> getByCustomerCardId(@RequestParam Integer card) {
        return ResponseEntity.ok(iPurchaseService.getByCustomerCardId(card));
    }

    @GetMapping(PurchaseApiConstants.PURCHASE_GET_DATE_BY_RANGE)
    private ResponseEntity<List<PurchaseDto>> getPurchaseByDatePurchaseBetween(@RequestParam("dateInitial") LocalDate dateInitial,
                                                                        @RequestParam("dateFinal")LocalDate dateFinal) {
        return new ResponseEntity<List<PurchaseDto>>(iPurchaseService.getByDatePurchaseBetween(dateInitial, dateFinal), HttpStatus.OK);
    }

    @GetMapping(PurchaseApiConstants.GET_CUSTOMER_BY_CARD_ID_AND_DATE_BY_RANGE)
    private ResponseEntity<List<PurchaseDto>> getCustomerByCardIdAndDateByRange(@RequestParam Integer customerCardId,  LocalDate dateInitial, LocalDate dateFinal) {
        return new ResponseEntity<List<PurchaseDto>>(iPurchaseService.getCustomerCardIdAndDatePurchaseBetween(customerCardId, dateInitial, dateFinal), HttpStatus.OK);
    }

    @PostMapping(PurchaseApiConstants.CREATE)
    private ResponseEntity<PurchaseDto> save(@RequestBody PurchaseDto purchaseDto) {
        return new ResponseEntity(iPurchaseService.save(purchaseDto), HttpStatus.CREATED);
    }

    @PutMapping(PurchaseApiConstants.UPDATE)
    private ResponseEntity<PurchaseDto> update(@RequestBody PurchaseDto purchaseDto) {
        return ResponseEntity.of(iPurchaseService.update(purchaseDto));
    }

    @DeleteMapping(PurchaseApiConstants.DELETE)
    private ResponseEntity<Boolean> update(@RequestParam Long purchaseId) {
        return new ResponseEntity<Boolean>(iPurchaseService.delete(purchaseId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}