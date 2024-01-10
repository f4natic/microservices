package com.example.service;

import com.example.model.Order;
import com.example.model.User;
import com.example.repository.OrderRepository;
import com.example.service.exception.OrderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class);

    @Value("${user-service.url}")
    private String userServiceUrl;

    @Autowired
    private OrderRepository orderRepository;

    public Order findOrderById(long id) {
        Optional<Order> optional = orderRepository.findById(id);
        return optional.get();
    }

    public Order createOrder(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = String.format("%s/users/email?email=%s", userServiceUrl, order.getUserEmail());
        logger.info(String.format("Connect with user-service on uri: %s", uri));
        User user = null;
        try {
            ResponseEntity<User> response = restTemplate.getForEntity(uri, User.class);
            user = response.getBody();
            if(user == null) {
                throw new OrderException(String.format("User with email:%s, not found", order.getUserEmail()));
            }
        }catch (Exception e) {
            throw new OrderException("Something went wrong, when getting user");
        }
        return orderRepository.save(order);
    }
}
