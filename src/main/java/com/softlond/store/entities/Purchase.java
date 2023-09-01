package com.softlond.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_purchase")
    private String codePurchase;

    @Column(name = "customerCardId")
    private Integer customerCardId;

    @Column(name = "total_products_purchase")
    private Integer totalNumberOfProductsPurchase; // cantidad de proudctos

    @Column(name = "totalPrice")
    private Double totalPrice; // total precio de la compra

    @Column(name = "date_purchase")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datePurchase;

    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.ALL})
    private List<PurchaseDetails> purchaseDetails;

    @ManyToOne()
    @JoinColumn(name = "customerCardId", insertable = false, updatable = false)
    private Customer customer;

}
