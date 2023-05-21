package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
               userDto.getUserId(),
               userDto.getFirstname(),
               userDto.getLastname(),
               userDto.getUsername(),
               userDto.getAddress(),
               userDto.getPhoneNumber(),
               userDto.getEmail(),
               userDto.isAuthorized()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.isAuthorized()
        );
    }

    public List<UserDto> mapToUserDtoList (final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
