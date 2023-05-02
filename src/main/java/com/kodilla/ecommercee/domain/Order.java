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

    private Long ID;
    private Date ORDER_DATE;
    private boolean IS_PAID;
    /**
    private User USER_LIST;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUSER_LIST() {
        return USER_LIST;
    }*/

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public Long getID() {
        return ID;
    }

    @Column(name = "ORDER_DATE")
    public Date getORDER_DATE() {
        return ORDER_DATE;
    }

    @Column(name = "IS_PAID")
    public boolean isIS_PAID() {
        return IS_PAID;
    }
}