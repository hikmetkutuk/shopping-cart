package com.shopping.inventory_service.controller;

import com.shopping.inventory_service.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam int quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
}
