package com.softlond.store.services.contracts;

import com.softlond.store.dto.PurchaseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPurchaseService {

    List<PurchaseDto> getAll();

    Optional<PurchaseDto> getById(Long purchaseId);

    List<PurchaseDto> getByCodePurchase(String codePurchase);

    List<PurchaseDto> getByCustomerCardId(Integer customerCardId);

    /**
     * Listar por rango de fechas
     * @param dateInitial Fecha inicio del rango
     * @param dateFinal Fecha final del rango
     * @return Lista de compras
     */
    List<PurchaseDto> getByDatePurchaseBetween(LocalDate dateInitial, LocalDate dateFinal);

    /**
     * Listar los registros por usuario y un rango de fecha
     * @param customerCardId Identificacion(cedula) de cliente a buscar
     * @param dateInitial Fecha inical del rango
     * @param dateFinal Fecha final del rango
     * @return Lista de compras buscada
     */
    List<PurchaseDto> getCustomerCardIdAndDatePurchaseBetween(Integer customerCardId, LocalDate dateInitial, LocalDate dateFinal);

    PurchaseDto save(PurchaseDto purchaseDto);

    Optional<PurchaseDto> update(PurchaseDto purchaseDto);

    Boolean delete(Long purchaseId);
}