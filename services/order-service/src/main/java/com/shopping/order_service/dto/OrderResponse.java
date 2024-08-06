package com.shopping.order_service.dto;

import java.math.BigDecimal;

public record OrderResponse(
        long id,
        String orderNumber,
        String skuCode,
        BigDecimal price,
        int quantity
) {
}
