package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String address;
    private String phoneNumber;
    private String email;
    private boolean isBlocked;

}
