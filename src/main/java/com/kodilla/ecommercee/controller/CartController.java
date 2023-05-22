package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductAddToCartException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
public class CartController {

    private final CartMapper cartMapper;

    private final CartService cartService;

    private final ProductService productService;

    private final OrderService orderService;

    private final OrderMapper orderMapper;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        cart.setTotalPrice(BigDecimal.ZERO);
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "{cartId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addToCart(@PathVariable Long cartId, @RequestBody Product product) throws ProductNotFoundException, CartNotFoundException, ProductAddToCartException {
        productService.getProduct(product.getProductId());
        productService.findProductPrice(product.getProductPrice());
        Cart cart = cartService.getProducts(cartId);
        cart.addProduct(product);
        cart.addPrice(product);
        cartService.saveCart(cart);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) throws CartNotFoundException {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cartId}/deleteProduct/{productId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) throws ProductNotFoundException, CartNotFoundException {
        Product product = productService.getProduct(productId);

        Cart cart = cartService.getProducts(cartId);
        cart.deleteProduct(product);
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getProducts(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.getProducts(cartId)));
    }

    @PostMapping(value = "{cartId}/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@PathVariable Long cartId, @RequestBody OrderDto orderDto) throws CartNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        order.setOrderDate(LocalDate.now());
        order.setPaid(false);
        orderService.saveOrder(order);

        Cart cart = cartService.getProducts(cartId);
        cart.setOrder(order);
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }
}

