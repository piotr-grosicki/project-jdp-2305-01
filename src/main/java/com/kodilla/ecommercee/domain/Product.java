package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    private Long productId;
    private String productName;
    private String productDescription;
    private int productQuantity;
    private BigDecimal productPrice;
    private Group group;
    //private List<Cart> cartList = new ArrayList<>();

    public Product(final String productName,
                   final String productDescription,
                   final int productQuantity,
                   final BigDecimal productPrice,
                   final Group group)
    {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.group = group;
    }

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "GROUP_ID")
    public Group getGroup(){return group;}

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "JOIN_PRODUCT_CART",
//            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
//            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")})
//    public List<Cart> getCart() {return cart;}
//
//    private void setCart(List<Cart> cart) {this.cart = cart;}
}
