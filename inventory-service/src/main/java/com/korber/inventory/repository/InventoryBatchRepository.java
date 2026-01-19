package com.korber.inventory.repository;

import com.korber.inventory.model.InventoryBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryBatchRepository
        extends JpaRepository<InventoryBatch, Long> {

    List<InventoryBatch> findByProductIdOrderByExpiryDateAsc(Long productId);
    Optional<InventoryBatch> findByBatchId(Long batchId);
}

