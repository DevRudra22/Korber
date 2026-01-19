package com.korber.order.client;

import com.korber.inventory.dto.InventoryResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public InventoryResponse getInventory(Long productId) {
        return restTemplate.getForObject(
                "http://localhost:8081/inventory/" + productId,
                InventoryResponse.class
        );
    }
}

