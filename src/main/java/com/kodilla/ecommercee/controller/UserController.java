package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserKeyGenerator;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserKeyGenerator userKeyGenerator;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        log.info("Created a new user id: " + userDto.getUserId());
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{id}/block")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long id) throws UserNotFoundException {
        log.info("Block a user id: " + id);
        UserDto userDto = userMapper.mapToUserDto(userService.findUserById(id));
        User user = userMapper.mapToUser(userDto);
        user.blockUser();
        userService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @PutMapping(value = "{id}/unblock")
    public ResponseEntity<UserDto> unblockUser(@PathVariable Long id) throws UserNotFoundException {
        log.info("Unblock a user id: " + id);
        UserDto userDto = userMapper.mapToUserDto(userService.findUserById(id));
        User user = userMapper.mapToUser(userDto);
        user.unblockUser();
        userService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }
    @GetMapping(value = "{id}/key")
    public ResponseEntity<Void> generateKey (@PathVariable Long id) throws UserNotFoundException {
        User user = userService.findUserById(id);
        userKeyGenerator.generateKey(user);
        return ResponseEntity.ok().build();
    }
}
