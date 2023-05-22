package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;

    public Cart getProducts(Long cardId) throws CartNotFoundException {
        return cartRepository.findById(cardId).orElseThrow(CartNotFoundException::new);
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteCart(Long cartId) throws CartNotFoundException {
        try {
            cartRepository.deleteById(cartId);
        } catch (Exception e) {
            throw new CartNotFoundException();
        }
    }


}
