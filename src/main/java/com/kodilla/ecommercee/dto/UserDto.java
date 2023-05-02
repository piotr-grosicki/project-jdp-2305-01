package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String userName;
    private boolean isAuthorized;
    private boolean keyIsActive;
}
