package com.example.service;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order findOrderById(long id) {
        Optional<Order> optional = orderRepository.findById(id);
        return optional.get();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
