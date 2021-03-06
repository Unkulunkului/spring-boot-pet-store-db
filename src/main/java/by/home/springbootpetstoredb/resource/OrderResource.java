package by.home.springbootpetstoredb.resource;

import by.home.springbootpetstoredb.entity.Order;
import by.home.springbootpetstoredb.entity.OrderStatusEnum;
import by.home.springbootpetstoredb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(path = "/store/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> save(@Valid @RequestBody Order order){
        Order save = orderService.save(order);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable("id") long id){
        Order byId = orderService.getById(id);
        return new ResponseEntity<>(byId ,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        orderService.deleteById(id);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @GetMapping(path = "/inventory")
    public ResponseEntity<Map<OrderStatusEnum, Long>> getInventory(){
        Map<OrderStatusEnum, Long> statusMap = orderService.getStatusMap();
        return new ResponseEntity<>(statusMap, HttpStatus.OK);
    }
}
