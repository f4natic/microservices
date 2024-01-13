package com.example.controller;

import com.example.model.ExceptionMessage;
import com.example.service.IService;
import com.example.service.OrderService;
import com.example.model.Order;
import com.example.service.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private IService<Order> orderService;

    @Autowired
    public OrderController(IService<Order> orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable long id) {
        return orderService.findById(id);
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderException.class)
    @ResponseBody
    ExceptionMessage handleBadRequest(Exception exception) {
        return new ExceptionMessage(exception.getMessage());
    }
}
