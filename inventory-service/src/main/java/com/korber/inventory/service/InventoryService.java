package com.korber.inventory.service;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.dto.InventoryUpdateRequest;

public interface InventoryService {
    InventoryResponse getInventory(Long productId);

    void updateInventory(InventoryUpdateRequest request);
}

