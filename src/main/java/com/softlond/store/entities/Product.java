package com.softlond.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Integer categoryId;

    @NotEmpty(message = "El nombre no debe ser vacío")
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price; // BigDecimal

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "active")
    private Integer active;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @ManyToOne()
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Categories category;

    @OneToMany(mappedBy = "product")
    private List<PurchaseDetails> purchaseDetails;

}