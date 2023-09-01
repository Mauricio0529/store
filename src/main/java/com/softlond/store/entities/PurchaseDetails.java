package com.softlond.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchase_details")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetails {

    @EmbeddedId
    private PurchaseDetailsPK purchaseDetailsPK;

    @Column(name = "nameProduct")
    private String nameProduct;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantityProduct")
    private Integer quantityProduct;

    @Column(name = "totalPriceProducts")
    private Double totalPriceProducts;

    @ManyToOne()
    @MapsId(value = "purchaseId")
    @JoinColumn(name = "purchase_id", insertable = false, updatable = false)
    private Purchase purchase;

    @ManyToOne()
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

}