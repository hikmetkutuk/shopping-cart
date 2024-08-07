package com.shopping.inventory_service.service;

import com.shopping.inventory_service.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean isInStock(String skuCode, int quantity) {
        try {
            var result = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
            log.info("Checking inventory for skuCode: " + skuCode);
            return result;
        } catch (Exception e) {
            log.error("Failed to check inventory for skuCode: " + skuCode, e);
            return false;
        }
    }
}
