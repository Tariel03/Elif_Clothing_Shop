package com.example.clothes.repositories;

import com.example.clothes.entities.Order;
import com.example.clothes.entities.Stock;
import com.example.clothes.entities.User;
import com.example.clothes.enums.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order>findByStock(Stock stock);
    List<Order>findByUser(User user);

}
