package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("Create user test")
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

    @DisplayName("Find all users test")
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

    @DisplayName("Find user by ID test")
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

    @DisplayName("Delete user by ID test")
    @Test
    void testDeleteById() {
        //Given
        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true, new ArrayList<>());
        User user2 = new User(2L, "Name2", "Lastname2", "Username2",
                "Address2", "123456780", "Mail2", true, new ArrayList<>());
        Order order = new Order(LocalDate.of(2023,2,2), true, user);

        //When
        userRepository.save(user);
        userRepository.save(user2);
        orderRepository.save(order);
        userRepository.deleteById(user.getUserId());

        //Then
        assertEquals(1, userRepository.count());
        assertEquals(0, orderRepository.count());
    }
}
