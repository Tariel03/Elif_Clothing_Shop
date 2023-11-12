package com.example.clothes.services.Impl;

import com.example.clothes.entities.Clothes;
import com.example.clothes.entities.Order;
import com.example.clothes.entities.Stock;
import com.example.clothes.entities.User;
import com.example.clothes.enums.OrderStatus;
import com.example.clothes.repositories.OrderRepository;
import com.example.clothes.repositories.StockRepository;
import com.example.clothes.repositories.UserRepository;
import com.example.clothes.services.CurrentUserService;
import com.example.clothes.services.Repo.OrderService;
import com.example.clothes.services.Repo.StockService;
import com.example.clothes.services.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final StockService stockService;
    private final CurrentUserService currentUserService;

    @Override
    public void save(Order order) {
        orderRepository.save(order);

    }

    @Override
    public void save(Long stock_id, Integer user_id) {
        Stock stock = stockService.findById(stock_id);
        Optional<User> optionalUser = userRepository.findById(user_id);
        if(optionalUser.isPresent()){
            Order order = new Order();
            order.setUser(optionalUser.get());
            order.setStock(stock);
            orderRepository.save(order);
        }
    }

    @Override
    public void save(Long stock_id) {
        Stock stock = stockService.findById(stock_id);
        Optional<User> optionalUser = userService.findByEmail(currentUserService.getCurrentUsername());
        if(optionalUser.isPresent()){
            Order order = new Order();
            order.setUser(optionalUser.get());
            order.setStock(stock);
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void delete(Long order_id) {

        Order order = findById(order_id);
        order.setOrderStatus(OrderStatus.DELETED);
        save(order);

    }

    @Override
    public void setDone(Long order_id) {
        Order order = findById(order_id);
        order.setOrderStatus(OrderStatus.DONE);
        save(order);

    }

    @Override
    public Order findById(Long id) {
        Optional<Order>optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        }
        throw new RuntimeException("No element by this id:%" + id);
    }
}
