package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
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
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createOrderTest() {
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);
        Order order1 = new Order(LocalDate.of(2023,4,2), true, user);

        //When
        userRepository.save(user);
        orderRepository.save(order1);

        //Then
        assertEquals(1, orderRepository.count());
    }

    @Test
    void getAllOrdersTest(){
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);
        Order order1 = new Order(LocalDate.of(2022,5,8),false, user);
        Order order2 = new Order(LocalDate.of(2023,3,2),true, user);
        Order order3 = new Order(LocalDate.of(2023,1,10),true, user);
        Order order4 = new Order(LocalDate.of(2023,3,20),true, user);

        //When
        userRepository.save(user);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);

        //Then
        assertEquals(4,orderRepository.findAll().size());
    }

    @Test
    void getOrderByIdTest(){
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);
        Order order1 = new Order(LocalDate.of(2022,5,8),false, user);
        Order order2 = new Order(LocalDate.of(2023,3,2),true, user);

        //When
        userRepository.save(user);
        orderRepository.save(order1);
        orderRepository.save(order2);

        //Then
        assertTrue(orderRepository.existsById(order1.getId()));
    }

    @Test
    void deleteOrder_andWithoutDeleteUserTest() {
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);
        Order order1 = new Order(LocalDate.of(2022,5,8),false, user);
        Order order2 = new Order(LocalDate.of(2023,3,2),true, user);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        //When
        userRepository.save(user);
        orderRepository.save(order1);
        orderRepository.save(order2);
        user.setOrderList(orderList);

        user.getOrderList().remove(order1);
        orderRepository.delete(order1);

        //Then
        assertEquals(1, orderRepository.count());
        assertEquals(1, userRepository.count());
    }

    @Test
    void orderModificationTest(){
        //Given
        User user = new User("Name", "Lastname", "Username",
                "Address", "123456789", "Mail", false);
        Order order1 = new Order(LocalDate.of(2022,5,8),false, user);
        Order order2 = new Order(LocalDate.of(2023,3,2),true, user);

        userRepository.save(user);
        orderRepository.save(order1);
        orderRepository.save(order2);

        //When
        order1.setPaid(true);
        order2.setOrderDate(LocalDate.of(2023,3,20));
        orderRepository.save(order1);
        orderRepository.save(order2);

        //Then
        assertEquals(20, orderRepository.findById(order2.getId()).get().getOrderDate().getDayOfMonth());
        assertTrue(orderRepository.findById(order2.getId()).get().isPaid());
    }
}
