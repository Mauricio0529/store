package com.softlond.store.repositoriesCrudJpa;

import com.softlond.store.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IPurchaseRepositoryCrudJpa extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByCustomerCardId(Integer cardId);

    List<Purchase> findAllByCodePurchase(String codePurchase);

    List<Purchase> findAllByDatePurchase(LocalDateTime dateTime);

    @Query(value = "SELECT * FROM purchase e WHERE DATE_TRUNC('day', e.date_purchase) >= :dateInitial AND " +
            "DATE_TRUNC('day', e.date_purchase) <= :dateFinal", nativeQuery = true)
    List<Purchase> findByDatePurchaseBetween(@Param("dateInitial") LocalDate dateInitial,
                                             @Param("dateFinal") LocalDate dateFinal);


    @Query(value = "SELECT * FROM purchase e WHERE " +
            "e.customer_card_id = :customerCardId AND " +
            "DATE_TRUNC('day', e.date_purchase) >= :dateInitial AND " +
            "DATE_TRUNC('day', e.date_purchase) <= :dateFinal", nativeQuery = true)
    List<Purchase> findByCustomerCardIdAndDatePurchaseBetween(@Param("customerCardId") Integer cardId,
                                                              @Param("dateInitial") LocalDate dateInitial,
                                                              @Param("dateFinal") LocalDate dateFinal);
}