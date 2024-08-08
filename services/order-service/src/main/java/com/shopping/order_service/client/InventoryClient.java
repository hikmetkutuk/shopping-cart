package com.shopping.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8093")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory/in-stock")
    boolean isInStock(@RequestParam String skuCode, @RequestParam int quantity);
}
