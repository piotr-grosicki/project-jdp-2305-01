package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.GenericEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    @GetMapping
    public List<GenericEntity> getProducts(){
        return new ArrayList<>();
    }

    @GetMapping(value = "{id}")
    public GenericEntity getProduct(Long productId){
        return new GenericEntity();
    }

    @PostMapping
    public void createProduct(GenericEntity product){}

    @PutMapping
    public GenericEntity updateProduct(GenericEntity product){
        return new GenericEntity();
    }

    @DeleteMapping
    public void deleteProduct(Long productId){}
}
