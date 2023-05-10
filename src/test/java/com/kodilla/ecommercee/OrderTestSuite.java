package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderTestSuite {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void createOrderTest() {
        //Given
        Order order1 = new Order(1L, LocalDate.of(2023,4,2),true,new User());
        //When
        orderRepository.save(order1);
        //Then
        assertEquals(1, orderRepository.count());
    }
    @Test
    void getAllOrdersTest(){
        //Given
        Order order1 = new Order(2L, LocalDate.of(2022,5,8),false,new User());
        Order order2 = new Order(3L, LocalDate.of(2023,3,2),true,new User());
        Order order3 = new Order(5L, LocalDate.of(2023,1,10),true,new User());
        Order order4 = new Order(7L, LocalDate.of(2023,3,20),true,new User());
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
        Order order1 = new Order(2L, LocalDate.of(2022,5,8),false,new User());
        Order order2 = new Order(4L, LocalDate.of(2023,3,2),true,new User());
        //When
        orderRepository.save(order1);
        orderRepository.save(order2);
        //Then
        assertTrue(orderRepository.existsById(order1.getId()));
    }
    @Test
    void deleteOrderTest(){
        //Given
        Order order1 = new Order(2L, LocalDate.of(2022,5,8),false,new User());
        Order order2 = new Order(4L, LocalDate.of(2023,3,2),true,new User());
        //When
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.deleteById(order1.getId());
        //Then
        assertEquals(1,orderRepository.findAll().size());
    }
    @Test
    void orderModificationTest(){
        //Given
        List<Order> orderList1 = new ArrayList<>();
        List<Order> orderList2 = new ArrayList<>();
        User user1 = new User(1L,"Ania", "Bania","aba","Aniowa 1", "1234", "a@b",true, orderList1);
        User user2 = new User(2L,"Hania", "Kania","abc","Baniowa 1", "1254", "ab@b",true, orderList2);
        Order order1 = new Order(2L, LocalDate.of(2022,5,8),false,user1);
        Order order2 = new Order(4L, LocalDate.of(2023,3,2),true,user2);
        orderList1.add(order1);
        orderList2.add(order2);
        orderRepository.save(order1);
        orderRepository.save(order2);
        userRepository.save(user1);
        userRepository.save(user2);
        //When
        order1.setPaid(true);
        order2.setUser(user1);
        orderRepository.save(order1);
        orderRepository.save(order2);
        //Then
        assertEquals("Bania", orderRepository.findById(order2.getId()).get().getUser().getLastname());
        assertTrue(orderRepository.findById(order2.getId()).get().isPaid());
        assertEquals(2, userRepository.findById(user1.getUserId()).get().getOrderList().size());
    }

}
