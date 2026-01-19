package com.korber.inventory.service.strategy;

import com.korber.inventory.model.InventoryBatch;

import java.util.List;

public interface InventoryStrategy {
    List<InventoryBatch> getBatches(Long productId);
}

