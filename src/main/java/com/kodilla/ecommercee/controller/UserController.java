package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1/users")
public class UserController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        log.info("Created a new user id: " + userDto.getId());
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(value = "{id}/block")
    public ResponseEntity<UserDto> blockUser(@PathVariable("id") Long id) {
        log.info("Block a user id: " + id);
        return ResponseEntity.ok(new UserDto(id,"Unit",false,true));
    }
}
