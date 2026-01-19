package com.korber.inventory.service;

import com.korber.inventory.dto.InventoryResponse;

public interface InventoryService {
    InventoryResponse getInventory(Long productId);
}

