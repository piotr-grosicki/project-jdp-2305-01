package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        //Given
        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true, new ArrayList<>());

        //When
        userRepository.save(user);

        //Then
        assertEquals(1, userRepository.count());
    }

    @Test
    void testFindAll() {
        //Given
        List<User> userList = new ArrayList<>();
        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true, new ArrayList<>());
        User user2 = new User(2L, "Name2", "Lastname2", "Username2",
                "Address2", "123456780", "Mail2", true, new ArrayList<>());
        userList.add(user);
        userList.add(user2);

        //When
        userRepository.save(user);
        userRepository.save(user2);

        //Then
        assertEquals(userList.size(), userRepository.findAll().size());
    }

    @Test
    void testFindById() {
        //Given
        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true, new ArrayList<>());

        //When
        userRepository.save(user);

        //Then
        assertTrue(userRepository.existsById(1L));
    }

    @Test
    void testDeleteById() {
        //Given
        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true, new ArrayList<>());
        User user2 = new User(2L, "Name2", "Lastname2", "Username2",
                "Address2", "123456780", "Mail2", true, new ArrayList<>());

        //When
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.deleteById(1L);

        //Then
        assertEquals(1, userRepository.count());
    }
}
