package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String address;
    private String phoneNumber;
    private String email;
    private boolean isAuthorized;

    private List<Order> orderList;

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "USER_ID", unique = true)
    public Long getUserId() {
        return userId;
    }

    @NonNull
    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    @NonNull
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    @NonNull
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    @NonNull
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @NonNull
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    @NonNull
    @Column(name = "IS_AUTHORIZED")
    public boolean isAuthorized() {
        return isAuthorized;
    }

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "userList",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Order> getOrderList() {
        return orderList;
    }
}
