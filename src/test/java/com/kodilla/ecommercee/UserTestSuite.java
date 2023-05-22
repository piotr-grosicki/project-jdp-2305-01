package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.UserKeyGenerator;
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
    @Autowired
    private UserKeyGenerator userKeyGenerator;

    @DisplayName("Create user test")
    @Test
    void testCreateUser() {
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);

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
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);
        User user2 = new User( "Name2", "Lastname2", "Username2",
                "Address2", "123456780", "Mail2", false);
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
        User user = new User(1L,"Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);

        //When
        userRepository.save(user);

        //Then
        assertTrue(userRepository.existsById(1L));
    }

    @DisplayName("Delete user by ID test")
    @Test
    void testDeleteById() {
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);
        User user2 = new User("Name2", "Lastname2", "Username2",
                "Address2", "123456780", "Mail2", false);
        Order order = new Order(LocalDate.of(2023,2,2), true, user);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        //When
        userRepository.save(user);
        userRepository.save(user2);
        orderRepository.save(order);
        user.setOrderList(orderList);
        userRepository.deleteById(user.getUserId());

        //Then
        assertEquals(1, userRepository.count());
        assertEquals(0, orderRepository.count());

    }

    @DisplayName("Key generator test")
    @Test
    void testKeyGenerator() {
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);

        //Then & When
        userKeyGenerator.generateKey(user);

    }

    @DisplayName("Key generator with blocked user test")
    @Test
    void TestKeyGeneratorWithBlockedUser() {
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);

        //Then & When
        user.blockUser();
        userKeyGenerator.generateKey(user);
    }
}
