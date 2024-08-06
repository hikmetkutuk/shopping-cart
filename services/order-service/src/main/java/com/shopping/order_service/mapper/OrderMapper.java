package com.shopping.order_service.mapper;

import com.shopping.order_service.dto.OrderRequest;
import com.shopping.order_service.dto.OrderResponse;
import com.shopping.order_service.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity()
        );
    }

    public Order toOrder(OrderRequest orderRequest) {
        if (orderRequest == null) {
            return null;
        }
        return Order.builder()
                .orderNumber(orderRequest.orderNumber())
                .skuCode(orderRequest.skuCode())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();
    }
}
