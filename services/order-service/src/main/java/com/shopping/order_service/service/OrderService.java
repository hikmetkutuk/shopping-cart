package com.shopping.order_service.service;

import com.shopping.order_service.dto.OrderRequest;
import com.shopping.order_service.dto.OrderResponse;
import com.shopping.order_service.mapper.OrderMapper;
import com.shopping.order_service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository orderRepository, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        try {
            var newOrder = orderRepository.save(mapper.toOrder(orderRequest));
            log.info("Order created successfully with id: {}", newOrder.getId());
            return mapper.fromOrder(newOrder);
        } catch (Exception e) {
            log.error("Unexpected error occurred while creating order: " + e.getMessage());
            throw new RuntimeException("Unexpected error occurred while creating order: " + e.getMessage());
        }
    }
}
