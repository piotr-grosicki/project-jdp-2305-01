package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public CartDto addToCart(@PathVariable int cartId, @PathVariable int productId) {
        return new CartDto();
    }
    @DeleteMapping(value = "{cartId}")
    public void deleteCart(@PathVariable int cartId) {
    }
    @DeleteMapping(value = "{cartId}/{productId}")
    public void deleteFromCart(@PathVariable int cartId, @PathVariable int productId) {
    }
    @GetMapping(value = "{cartId}/{productId}")
    public CartDto getProduct(@PathVariable int cartId, @PathVariable int productId) {
        return new CartDto();
    }
    @GetMapping
    public List<CartDto> getProductsList() {
        return new ArrayList<>();
    }
}

