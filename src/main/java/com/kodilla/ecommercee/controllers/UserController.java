package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userDto;
    }

    @PutMapping(value = "{id}/block")
    public UserDto blockUser(@PathVariable("id") Long id) {
        return new UserDto(id,"UserName",false,false);
    }
}
