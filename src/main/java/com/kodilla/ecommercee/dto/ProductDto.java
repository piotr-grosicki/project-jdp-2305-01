package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private String productName;
    private String productDescription;
    private int productQuantity;
    private BigDecimal productPrice;
    private Group group;

}