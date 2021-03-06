package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Order;
import by.home.springbootpetstoredb.entity.OrderStatusEnum;
import by.home.springbootpetstoredb.exception.NotFoundException;
import by.home.springbootpetstoredb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Order getById(long id){
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new NotFoundException("This order doesn't exist!");
    }

    public void deleteById(long id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }else {
            throw new NotFoundException("Order doesn't exist!");
        }
    }

    public Map<OrderStatusEnum, Long> getStatusMap(){
        List<Order> allOrder = orderRepository.findAll();
        Map<OrderStatusEnum, Long> statusMap =  allOrder.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
        return statusMap;
    }

}
