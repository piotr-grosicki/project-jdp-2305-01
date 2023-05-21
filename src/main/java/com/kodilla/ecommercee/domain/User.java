package com.kodilla.ecommercee.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Setter
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

    public User(Long userId, String firstname, String lastname, String username, String address,
                String phoneNumber, String email, Boolean isAuthorized){
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isAuthorized = true;
    }

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

    public void blockUser() {
        this.isAuthorized = false;
    }
    public void unblockUser() {
        this.isAuthorized = true;
    }

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER
    )
    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", isAuthorized=" + isAuthorized +
                ", orderList=" + orderList +
                '}';
    }
}
