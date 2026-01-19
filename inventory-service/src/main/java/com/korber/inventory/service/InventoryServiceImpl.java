package com.korber.inventory.service;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.dto.InventoryUpdateRequest;
import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.repository.InventoryBatchRepository;
import com.korber.inventory.service.strategy.InventoryStrategy;
import com.korber.inventory.service.strategy.InventoryStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryStrategyFactory factory;
    private final InventoryBatchRepository repository;

    public InventoryServiceImpl(InventoryBatchRepository repository,
                                InventoryStrategyFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public InventoryResponse getInventory(Long productId) {
        InventoryStrategy strategy = factory.getStrategy("EXPIRY");
        List<InventoryBatch> batches = strategy.getBatches(productId);

        return InventoryResponse.from(batches);
    }

    @Override
    public void updateInventory(InventoryUpdateRequest request) {

        for (InventoryUpdateRequest.BatchReservation reservation
                : request.getReservations()) {

            InventoryBatch batch = repository.findById(reservation.getBatchId())
                    .orElseThrow(() ->
                            new IllegalStateException(
                                    "Batch not found: " + reservation.getBatchId()));

            if (batch.getQuantity() < reservation.getQuantity()) {
                throw new IllegalStateException(
                        "Insufficient quantity in batch: " + batch.getBatchId());
            }

            // Deduct quantity
            batch.setQuantity(batch.getQuantity() - reservation.getQuantity());

            repository.save(batch);
        }
    }
}

