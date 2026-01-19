package com.korber.inventory.controller;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.dto.InventoryUpdateRequest;
import com.korber.inventory.dto.InventoryUpdateResponse;
import com.korber.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/{productId}")
    public InventoryResponse getInventory(@PathVariable Long productId) {
        return service.getInventory(productId);
    }

    @PostMapping("/update")
    public InventoryUpdateResponse updateInventory(
            @RequestBody InventoryUpdateRequest request) {

        service.updateInventory(request);

        return new InventoryUpdateResponse(
                request.getProductId(),
                "Inventory updated successfully"
        );
    }
}

