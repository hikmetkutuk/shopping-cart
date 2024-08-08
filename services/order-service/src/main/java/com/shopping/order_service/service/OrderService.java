package com.shopping.order_service.service;

import com.shopping.order_service.client.InventoryClient;
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
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, OrderMapper mapper, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.inventoryClient = inventoryClient;
    }

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        var isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        try {
            if(isInStock) {
                var newOrder = orderRepository.save(mapper.toOrder(orderRequest));
                log.info("Order created successfully with id: {}", newOrder.getId());
                return mapper.fromOrder(newOrder);
            } else {
                log.error("Insufficient quantity in stock: {}", orderRequest.skuCode());
                throw new RuntimeException("Insufficient quantity in stock: " + orderRequest.skuCode());
            }
        } catch (Exception e) {
            log.error("Unexpected error occurred while creating order: " + e.getMessage());
            throw new RuntimeException("Unexpected error occurred while creating order: " + e.getMessage());
        }
    }
}
