package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    private Long id;
    private LocalDate orderDate;
    private boolean isPaid;

    private User user;

    public Order(LocalDate orderDate, boolean isPaid, User user) {
        this.orderDate = orderDate;
        this.isPaid = isPaid;
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "ORDER_DATE")
    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Column(name = "IS_PAID")
    public boolean isPaid() {
        return isPaid;
    }
}