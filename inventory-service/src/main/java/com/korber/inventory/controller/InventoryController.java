package com.korber.inventory.controller;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/{productId}")
    public InventoryResponse getInventory(@PathVariable Long productId) {
        return service.getInventory(productId);
    }
}

