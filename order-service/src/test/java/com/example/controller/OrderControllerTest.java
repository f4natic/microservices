package com.example.controller;

import com.example.model.Order;
import com.example.service.IService;
import com.example.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IService<Order> service;

    @Test
    public void shouldCreateOrder() throws Exception {
        Order order = Order.builder()
                .product("Example Product")
                .userEmail("example@example.com")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);

        when(service.save(order)).thenReturn(order);

        MockHttpServletResponse response = mockMvc.perform(post("/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk()).andReturn().getResponse();
        Order returnedOrder = objectMapper.readValue(response.getContentAsString(), Order.class);
        Assertions.assertEquals(order, returnedOrder);
    }
}
