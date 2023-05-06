package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private LocalDate orderDate;
    private boolean isPaid;
}
