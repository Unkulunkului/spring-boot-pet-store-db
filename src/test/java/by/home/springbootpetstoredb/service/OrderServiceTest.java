package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Order;
import by.home.springbootpetstoredb.entity.OrderStatusEnum;
import by.home.springbootpetstoredb.repository.OrderRepository;
import org.hibernate.sql.ordering.antlr.OrderByFragmentTranslator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderServiceTest {


    @Autowired
    private OrderRepository orderRepository;
    private Order order;
    private Order order2;
    private Order order3;


    @BeforeEach
    void createOrder(){
        order = new Order(1, 1, "2020-10-10", OrderStatusEnum.DELIVERED, true);
    }

    @Test
    void save() {
        Order save = orderRepository.save(order);
        assertEquals(order, save);
    }

    @Test
    void getById() {
        orderRepository.save(order);
        Optional<Order> byId = orderRepository.findById(order.getId());
        assertEquals(order, byId.orElse(new Order()));
    }

    @Test
    void deleteById() {
        orderRepository.save(order);
        orderRepository.deleteById(order.getId());
        Optional<Order> byId = orderRepository.findById(order.getId());
        assertEquals(false, byId.isPresent());
    }

    @Test
    void getStatusMap() {
        order2 = new Order(1, 1, "2020-10-10", OrderStatusEnum.PLACED, true);
        order3 = new Order(1, 1, "2020-10-10", OrderStatusEnum.APPROVED, true);
        Map<OrderStatusEnum, Long> expected = new HashMap<>();


        expected.put(OrderStatusEnum.DELIVERED, 1L);
        expected.put(OrderStatusEnum.PLACED, 1L);
        expected.put(OrderStatusEnum.APPROVED, 1L);


        orderRepository.save(order);
        orderRepository.save(order2);
        orderRepository.save(order3);

        Map<OrderStatusEnum, Long> actual = orderRepository.findAll().stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
        assertEquals(expected, actual);

    }
}