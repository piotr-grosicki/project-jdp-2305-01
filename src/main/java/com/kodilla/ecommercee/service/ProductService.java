package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProduct(final Long productId) throws ProductNotFoundException {
        return repository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return repository.save(product);
    }

    public void deleteProduct(final Long id) {
        repository.deleteById(id);
    }

    public ProductDto updateProduct(final ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        return productMapper.mapToProductDto(repository.save(product));
    }

    public Object deleteProduct(Product byProductId) {
        Long id = byProductId.getProductId();
        repository.deleteById(id);
        return null;
    }
}
