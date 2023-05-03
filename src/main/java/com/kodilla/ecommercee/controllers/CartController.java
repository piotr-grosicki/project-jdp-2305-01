package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @GetMapping
    public List<CartDto> getCarts() {
        return new ArrayList<>();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto addCart(@RequestBody CartDto cartDto)  {
        return cartDto;
    }
    @GetMapping(value = "{cartId}")
    public CartDto updateCart(@PathVariable int cartId) {
        return new CartDto();
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto updateCart(@RequestBody CartDto cartDto) {
        return new CartDto();
    }
    @DeleteMapping(value = "{cartId}")
    public void deleteCart(@PathVariable int cartId) {
    }
}
