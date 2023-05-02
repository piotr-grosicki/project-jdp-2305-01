package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts(){
        return new ArrayList<>();
    }

    @GetMapping(value = "{id}")
    public ProductDto getProduct(Long productId){
        return new ProductDto();
    }

    @PostMapping
    public void createProduct(ProductDto product){}

    @PutMapping
    public ProductDto updateProduct(ProductDto product){
        return new ProductDto();
    }

    @DeleteMapping
    public void deleteProduct(Long productId){}
}
