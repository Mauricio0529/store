package com.softlond.store.repositories.contracts;

import com.softlond.store.dto.PurchaseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPurchaseRepository {

    List<PurchaseDto> getAll();

    Optional<PurchaseDto> getById(Long purchaseId);

    List<PurchaseDto> getByCodePurchase(String codePurchase);

    List<PurchaseDto> getByCustomerCardId(Integer customerCardId);

    /**
     * Listar por fecha, ej: liste las compras del año 2022
     * @param dateInitial @return Lista de compras
     * @param dateFinal
     */
    List<PurchaseDto> getByDatePurchaseBetween(LocalDate dateInitial, LocalDate dateFinal);

    /**
     * Listar los registros por usuario y un rango de fecha
     * @param customerCardId Identificacion de cliente a buscar
     * @param dateInitial Fecha inical del rango
     * @param dateFinal Fecha final del rango
     * @return Lista de compras buscada
     */
    List<PurchaseDto> getByCustomerCardIdAndDatePurchaseBetween(Integer customerCardId, LocalDate dateInitial, LocalDate dateFinal);

    PurchaseDto save(PurchaseDto purchaseDto);

    void delete(Long purchaseId);
}