package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private LocalDate orderDate;
    private boolean isPaid;
    private User user;
}
