package by.home.springbootpetstoredb.repository;

import by.home.springbootpetstoredb.entity.Order;
import by.home.springbootpetstoredb.entity.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsById(long id);
}
