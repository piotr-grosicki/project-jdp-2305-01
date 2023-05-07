package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCT")
public class Product {

    private Long productId;
    private String productName;
    private String productDescription;
    private int productQuantity;
    private BigDecimal productPrice;

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "PRODUCT_ID",unique = true)
    public Long getProductId(){return productId;}

    @NonNull
    @Column(name = "PRODUCT_NAME")
    public String getProductName(){return productName;}

    @NonNull
    @Column(name = "PRODUCT_DESCRIPTION")
    public String getProductDescription(){return productDescription;}

    @NonNull
    @Column(name = "PRODUCT_QUANTITY")
    public int getProductQuantity(){return productQuantity;}

    @NonNull
    @Column(name = "PRODUCT_PRICE")
    public BigDecimal getProductPrice(){return productPrice;}
}
