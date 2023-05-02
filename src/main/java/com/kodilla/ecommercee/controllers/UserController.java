package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.dto.GenericEntityDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericEntityDto addUser(@RequestBody GenericEntityDto genericEntityDto) {
        return genericEntityDto;
    }

    @PutMapping(value = "{userId}/block")
    public GenericEntityDto blockUser(@PathVariable("userId") Long id) {
        return new GenericEntityDto();
    }

    @PutMapping(value = "{userId}/generate")
    public GenericEntityDto generateKey(@PathVariable("userId") Long id) {
        return new GenericEntityDto();
    }
}
