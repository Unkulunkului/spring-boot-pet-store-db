package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Order;
import by.home.springbootpetstoredb.entity.OrderStatusEnum;
import by.home.springbootpetstoredb.exception.NotFoundException;
import by.home.springbootpetstoredb.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order){
        Order save = orderRepository.save(order);
        log.info(order+"was add to repository");
        return save;
    }

    public Order getById(long id){
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            log.info("Returned order with id="+id);
            return byId.get();
        }
        log.warn("Try to get order with non-existent id="+id);
        throw new NotFoundException("This order doesn't exist!");
    }

    public void deleteById(long id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            log.info("Order with id="+id+"was deleted");
        }else {
            log.warn("Try to delete order with non-existent id="+id);
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
