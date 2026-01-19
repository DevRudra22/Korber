package com.korber.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryUpdateResponse {

    private Long productId;
    private String message;
}
