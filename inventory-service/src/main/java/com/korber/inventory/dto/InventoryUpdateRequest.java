package com.korber.inventory.dto;

import lombok.Data;
import java.util.List;

@Data
public class InventoryUpdateRequest {

    private Long productId;
    private List<BatchReservation> reservations;

    @Data
    public static class BatchReservation {
        private Long batchId;
        private Integer quantity;
    }
}
