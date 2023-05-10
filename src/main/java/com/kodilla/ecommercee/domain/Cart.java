package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CART")
public class Cart {
    Long cartId;
    BigDecimal totalPrice;

    @Id
    @GeneratedValue
    @Column(name = "CART_ID",unique = true)
    public Long getCartId() {
        return cartId;
    }
    @NotNull
    @Column(name = "TOTAL_PRICE")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    /*
    @ManyToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY)
    )
    private List<Product> productsList = new ArrayList<>();

     */
}