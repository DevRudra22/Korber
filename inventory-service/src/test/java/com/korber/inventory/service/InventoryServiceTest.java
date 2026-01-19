/*
package com.korber.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.repository.InventoryBatchRepository;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class InventoryServiceTest {

  @Test
  void reserveAndUpdate_deductsFromEarliestBatches() {
    InventoryBatchRepository repo = mock(InventoryBatchRepository.class);
    InventoryAllocationStrategyFactory factory = mock(InventoryAllocationStrategyFactory.class);
    InventoryAllocationStrategy strategy = mock(InventoryAllocationStrategy.class);

    when(factory.getOrDefault(null)).thenReturn(strategy);

    InventoryBatch b1 =
        new InventoryBatch(1L, 1001L, "Laptop", 5, LocalDate.parse("2026-01-01"));
    InventoryBatch b2 =
        new InventoryBatch(2L, 1001L, "Laptop", 5, LocalDate.parse("2026-02-01"));

    when(repo.findByProductIdOrderByExpiryDateAsc(1001L)).thenReturn(List.of(b1, b2));
    Map<Long, Integer> deductions = new LinkedHashMap<>();
    deductions.put(1L, 5);
    deductions.put(2L, 2);
    when(strategy.allocate(anyList(), eq(7))).thenReturn(AllocationPlan.of(deductions));

    InventoryService service = new InventoryService(repo, factory);
    var resp =
        service.reserveAndUpdate(
            new InventoryUpdateRequest(1001L, 7, null));

    assertThat(resp.getReservedQuantity()).isEqualTo(7);
    assertThat(resp.getReservedFromBatchIds()).containsExactly(1L, 2L);
    assertThat(b1.getQuantity()).isEqualTo(0);
    assertThat(b2.getQuantity()).isEqualTo(3);
    verify(repo).saveAll(anyList());
  }

  @Test
  void reserveAndUpdate_throwsWhenInsufficient() {
    InventoryBatchRepository repo = mock(InventoryBatchRepository.class);
    InventoryAllocationStrategyFactory factory = mock(InventoryAllocationStrategyFactory.class);
    InventoryAllocationStrategy strategy = mock(InventoryAllocationStrategy.class);

    when(factory.getOrDefault(null)).thenReturn(strategy);

    InventoryBatch b1 =
        new InventoryBatch(1L, 1001L, "Laptop", 2, LocalDate.parse("2026-01-01"));

    when(repo.findByProductIdOrderByExpiryDateAsc(1001L)).thenReturn(List.of(b1));
    when(strategy.allocate(anyList(), eq(3))).thenReturn(AllocationPlan.of(Map.of(1L, 2)));

    InventoryService service = new InventoryService(repo, factory);

    assertThatThrownBy(
            () ->
                service.reserveAndUpdate(
                    new InventoryUpdateRequest(1001L, 3, null)))
        .isInstanceOf(InsufficientInventoryException.class);
  }
}


*/
