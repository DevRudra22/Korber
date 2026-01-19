package com.korber.inventory.api;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.service.InventoryServiceImpl;
import com.korber.inventory.service.strategy.InventoryStrategy;
import com.korber.inventory.service.strategy.InventoryStrategyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryStrategyFactory strategyFactory;

    @Mock
    private InventoryStrategy inventoryStrategy;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    void shouldReturnInventorySortedByExpiry() {

        InventoryBatch batch = new InventoryBatch();
        batch.setBatchId(1L);
        batch.setProductId(1001L);
        batch.setProductName("Laptop");
        batch.setQuantity(50);
        batch.setExpiryDate(LocalDate.now().plusDays(10));

        when(strategyFactory.getStrategy("EXPIRY")).thenReturn(inventoryStrategy);
        when(inventoryStrategy.getBatches(1001L)).thenReturn(List.of(batch));

        InventoryResponse response = inventoryService.getInventory(1001L);

        assertEquals(1001L, response.getProductId());
        assertEquals("Laptop", response.getProductName());
        assertEquals(1, response.getBatches().size());
    }
}



