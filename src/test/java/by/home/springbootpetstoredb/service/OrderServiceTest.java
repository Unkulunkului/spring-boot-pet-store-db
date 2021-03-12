package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Order;
import by.home.springbootpetstoredb.entity.OrderStatusEnum;
import by.home.springbootpetstoredb.repository.OrderRepository;
import org.hibernate.sql.ordering.antlr.OrderByFragmentTranslator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;


    @Test
    void save() {
        Order order = new Order(1, 1, "2020-10-10", OrderStatusEnum.APPROVED, true);
        Order save = orderRepository.save(order);
        assertEquals(order, save);
    }

    @Test
    void getById() {
        Order order = new Order(1, 1, "2020-10-10", OrderStatusEnum.APPROVED, true);
        orderRepository.save(order);
        Optional<Order> byId = orderRepository.findById(1L);
        assertEquals(order, byId.get());
    }

    @Test
    void deleteById() {
        Order order = new Order(1, 1, "2020-10-10", OrderStatusEnum.APPROVED, true);
        orderRepository.save(order);
        orderRepository.deleteById(1L);
        Optional<Order> byId = orderRepository.findById(1L);
        ///????
    }

    @Test
    void getStatusMap() {
        Map<OrderStatusEnum, Long> expected = new HashMap<>();
        expected.put(OrderStatusEnum.DELIVERED, 1L);
        expected.put(OrderStatusEnum.PLACED, 1L);
        expected.put(OrderStatusEnum.APPROVED, 1L);

        Order order1 = new Order(1, 1, "2020-10-10", OrderStatusEnum.DELIVERED, true);
        Order order2 = new Order(1, 1, "2020-10-10", OrderStatusEnum.PLACED, true);
        Order order3 = new Order(1, 1, "2020-10-10", OrderStatusEnum.APPROVED, true);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        Map<OrderStatusEnum, Long> actual = orderRepository.findAll().stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
        assertEquals(expected, actual);

    }
}