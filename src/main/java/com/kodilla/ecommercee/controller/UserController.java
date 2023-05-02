package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GenericEntityDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommercee/users")
public class UserController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericEntityDto addUser(@RequestBody GenericEntityDto genericEntityDto) {
        return genericEntityDto;
    }

    @PutMapping(value = "{id}/block")
    public GenericEntityDto blockUser(@PathVariable("id") Long id) {
        return new GenericEntityDto(id, "user is blocked");
    }

    @PutMapping(value = "{id}/generate")
    public GenericEntityDto generateKey(@PathVariable("id") Long id) {
        return new GenericEntityDto(id, "key generated");
    }
}
