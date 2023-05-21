package com.kodilla.ecommercee;

import com.google.gson.Gson;
import com.kodilla.ecommercee.controller.UserController;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserKeyGenerator;
import com.kodilla.ecommercee.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserController userController;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private UserKeyGenerator userKeyGenerator;

    @DisplayName("Add user test")
    @Test
    void testAddUser() throws UserNotFoundException, Exception {
        //Given
        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true);

        UserDto userDto = new UserDto(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true);

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.saveUser(user)).thenReturn(user);

        //When & Then
        Gson gson = new Gson();
        String content = gson.toJson(userDto);

        //Then & When
        mockMvc
                .perform(MockMvcRequestBuilders
                                .post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @DisplayName("Block user test")
    @Test
    void testBlockUsers() throws UserNotFoundException, Exception {
        //Given
        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true);
        User user1 = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);

        UserDto userDto = new UserDto(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true);
        UserDto userDto1 = new UserDto(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);

        when(userMapper.mapToUserDto(userService.findUserById(user.getUserId()))).thenReturn(userDto);
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        user.setAuthorized(false);
        when(userService.saveUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(userService.saveUser(user1))).thenReturn(userDto1);

        //Then & When

        Gson gson = new Gson();
        String content = gson.toJson(userDto1);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/users/1/block")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isAuthorized", Matchers.is(false)));
    }

    @DisplayName("Block user with no exist id test")
    @Test
    void testBlockUserWithNoExistId() throws UserNotFoundException, Exception {
            //Given
            Long userId = 1L;
            doThrow(UserNotFoundException.class).when(userService).findUserById(userId);


            //Then & When

            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/v1/users/{id}/block", userId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @DisplayName("Generate random key test")
    @Test
    void testGenerateKey() throws UserNotFoundException, Exception {
        //Given
        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true);

        //Then & When
        when(userService.saveUser(user)).thenReturn(user);
        userKeyGenerator.generateKey(user);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("v1/users/1/key")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
