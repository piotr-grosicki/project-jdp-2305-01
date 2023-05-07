package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    @GetMapping
    public List<OrderDto> getOrders(){
        return new ArrayList<>();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto){

    }
    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable int orderId) {
        return new OrderDto(1L,LocalDate.of(2023,1,4),true);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return new OrderDto(2L,LocalDate.of(2023,1,12),false);
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable int orderId){

    }
}
