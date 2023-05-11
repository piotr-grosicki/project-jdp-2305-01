package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    private Long id;
    private Date orderDate;
    private boolean isPaid;

    private User userList;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUserList() {
        return userList;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "ORDER_DATE")
    public Date getOrderDate() {
        return orderDate;
    }

    @Column(name = "IS_PAID")
    public boolean isPaid() {
        return isPaid;
    }
}