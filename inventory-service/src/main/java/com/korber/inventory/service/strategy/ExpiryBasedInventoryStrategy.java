package com.korber.inventory.service.strategy;

import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.repository.InventoryBatchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("EXPIRY")
public class ExpiryBasedInventoryStrategy implements InventoryStrategy {

    private final InventoryBatchRepository repository;

    public ExpiryBasedInventoryStrategy(InventoryBatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InventoryBatch> getBatches(Long productId) {
        return repository.findByProductIdOrderByExpiryDateAsc(productId);
    }
}

