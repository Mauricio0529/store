package com.softlond.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "cardId")
    private Integer cardId;

    @NotEmpty(message = "El nombre no debe ser vacío")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "El apellido no debe ser vacío")
    @Column(name = "lastName")
    private String lastName;

    @NotEmpty(message = "El email no debe ser vacío")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "La contraseña no debe ser vacío")
    @Column(name = "password")
    private String password;

    private String rol;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Purchase> purchaseList;
}