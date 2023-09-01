package com.softlond.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private Long id;

    private Integer categoryId;

    private String name;

    private Float price;

    private Integer stock;

    private Integer active;

}