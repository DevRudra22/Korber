package com.korber.inventory.dto;

import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.model.InventoryBatch;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class InventoryResponse {

    private Long productId;
    private String productName;
    private List<BatchResponse> batches;

    public static InventoryResponse from(List<InventoryBatch> inventoryBatches) {

        InventoryResponse response = new InventoryResponse();

        if (inventoryBatches == null || inventoryBatches.isEmpty()) {
            response.setBatches(List.of());
            return response;
        }

        InventoryBatch first = inventoryBatches.get(0);

        response.setProductId(first.getProductId());
        response.setProductName(first.getProductName());

        response.setBatches(
                inventoryBatches.stream()
                        .map(batch ->
                                new BatchResponse(
                                        batch.getBatchId(),
                                        batch.getQuantity(),
                                        batch.getExpiryDate()
                                )
                        )
                        .collect(Collectors.toList())
        );

        return response;
    }
}

