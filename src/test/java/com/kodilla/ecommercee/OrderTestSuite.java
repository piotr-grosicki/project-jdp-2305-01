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
        Order order1 = new Order(LocalDate.of(2023,4,2), true, new User());

        //When
        orderRepository.save(order1);

        //Then
        assertEquals(1, orderRepository.count());
    }

    @Test
    void getAllOrdersTest(){
        //Given
        Order order1 = new Order(LocalDate.of(2022,5,8),false, new User());
        Order order2 = new Order(LocalDate.of(2023,3,2),true, new User());
        Order order3 = new Order(LocalDate.of(2023,1,10),true, new User());
        Order order4 = new Order(LocalDate.of(2023,3,20),true, new User());

        //When
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
        Order order1 = new Order(LocalDate.of(2022,5,8),false, new User());
        Order order2 = new Order(LocalDate.of(2023,3,2),true, new User());

        //When
        orderRepository.save(order1);
        orderRepository.save(order2);

        //Then
        assertTrue(orderRepository.existsById(order1.getId()));
    }

    @Test
    void deleteOrder_andWithoutDeleteUserTest() {
        //Given
        Order order1 = new Order(LocalDate.of(2022,5,8),false, new User());
        Order order2 = new Order(LocalDate.of(2023,3,2),true, new User());

        User user = new User(1L, "Name", "Lastname", "Username",
                "Address", "123456789", "Mail", true, new ArrayList<>());

        //When
        userRepository.save(user);
        orderRepository.save(order1);
        orderRepository.save(order2);

        user.getOrderList().remove(order1);
        orderRepository.delete(order1);

        //Then
        assertEquals(1, orderRepository.count());
        assertEquals(2, userRepository.count());
    }

    @Test
    void orderModificationTest(){
        //Given
        Order order1 = new Order(LocalDate.of(2022,5,8),false, new User());
        Order order2 = new Order(LocalDate.of(2023,3,2),true, new User());

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
