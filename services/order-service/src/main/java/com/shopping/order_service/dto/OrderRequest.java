package com.shopping.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(
        String orderNumber,
        String skuCode,
        BigDecimal price,
        int quantity
) {
}
