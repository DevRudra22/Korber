package com.korber.inventory.service;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.service.strategy.InventoryStrategy;
import com.korber.inventory.service.strategy.InventoryStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryStrategyFactory factory;

    public InventoryServiceImpl(InventoryStrategyFactory factory) {
        this.factory = factory;
    }

    @Override
    public InventoryResponse getInventory(Long productId) {
        InventoryStrategy strategy = factory.getStrategy("EXPIRY");
        List<InventoryBatch> batches = strategy.getBatches(productId);

        return InventoryResponse.from(batches);
    }
}

