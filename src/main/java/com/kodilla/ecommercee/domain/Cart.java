package com.kodilla.ecommercee.domain;


import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;


@Setter
@NoArgsConstructor
@Entity
@Table(name = "CART")
public class Cart {
    private Long cartId;
    private BigDecimal totalPrice;
    private List<Product> productsList = new ArrayList<>();

    private Order order;


    public Cart(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CART_ID",unique = true)
    public Long getCartId() {
        return cartId;
    }

    @Column(name = "TOTAL_PRICE")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }


    @ManyToMany(cascade =  CascadeType.PERSIST)
    public List<Product> getProductsList() {
        return productsList;
    }


    public void addProduct(Product product) {
            this.productsList.add(product);
            product.getCart().add(this);
    }

    public void deleteProduct(Product product) {
        this.productsList.remove(product);
        product.getCart().remove(this);
    }

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public void addPrice(Product product) {
        totalPrice = BigDecimal.ZERO;
        for (int i = 0; i < productsList.size(); i++) {
            totalPrice = totalPrice.add(product.getProductPrice());
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", totalPrice=" + totalPrice +
                ", productsList=" + productsList +
                ", order=" + order +
                '}';
    }
}