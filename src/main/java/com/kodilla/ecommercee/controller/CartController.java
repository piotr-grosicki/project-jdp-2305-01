package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto createCart(@RequestBody CartDto cartDto)  {
        return cartDto;
    }
    @PostMapping(value = "{cartId}/{productId}")
    public CartDto addToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return new CartDto(1,new BigDecimal(20));
    }
    @DeleteMapping(value = "{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
    }
    @DeleteMapping(value = "{cartId}/{productId}")
    public void deleteFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
    }
    @GetMapping(value = "{cartId}")
    public List<CartDto> getProducts(@PathVariable Long cartId) {
        return new ArrayList<>();
    }
    @PostMapping(value = "{cartId}/order")
    public void createOrder(@PathVariable Long cartId, @RequestBody Order order) {}
}

