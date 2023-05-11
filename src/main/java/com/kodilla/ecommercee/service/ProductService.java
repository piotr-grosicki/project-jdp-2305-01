package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.TaskNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProduct(final Long productId) throws TaskNotFoundException {
        return repository.findById(productId).orElseThrow(TaskNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return repository.save(product);
    }

    public void deleteProduct(final Long id) {
        repository.deleteById(id);
    }
}
