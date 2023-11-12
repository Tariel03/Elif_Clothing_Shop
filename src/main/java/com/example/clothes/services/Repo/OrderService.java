package com.example.clothes.services.Repo;

import com.example.clothes.entities.Clothes;
import com.example.clothes.entities.Order;
import com.example.clothes.entities.User;
import com.example.clothes.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    void save(Order order);
    void save(Long stock_id, Integer user_id);
    void save(Long stock_id);

    List<Order> findAll();
    List<Order>findByOrderStatus(OrderStatus orderStatus);
    List<Order>findByUser(User user);
    void delete(Long order_id);
    void setDone(Long order_id);
    Order findById(Long id);
}
