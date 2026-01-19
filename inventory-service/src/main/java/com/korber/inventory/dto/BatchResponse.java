package com.korber.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BatchResponse {

    private Long batchId;
    private Integer quantity;
    private LocalDate expiryDate;
}
