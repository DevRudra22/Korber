/*
package src.test.java.com.korber.order.service;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.service.InventoryServiceImpl;
import com.korber.inventory.service.strategy.InventoryStrategy;
import com.korber.inventory.service.strategy.InventoryStrategyFactory;
import com.korber.order.client.InventoryClient;
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
class OrderServiceTest {

    @Mock
    private InventoryClient inventoryClient;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPlaceOrderSuccessfully() {

        // GIVEN
        OrderRequest request = new OrderRequest();
        request.setProductId(1002L);
        request.setQuantity(3);

        BatchResponse batch = new BatchResponse(9L, 10, LocalDate.now().plusDays(10));
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setProductId(1002L);
        inventoryResponse.setProductName("Smartphone");
        inventoryResponse.setBatches(List.of(batch));

        when(inventoryClient.getInventory(1002L)).thenReturn(inventoryResponse);
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        // WHEN
        OrderResponse response = orderService.placeOrder(request);

        // THEN
        assertNotNull(response);
        assertEquals("PLACED", response.getStatus());
        assertEquals(3, response.getQuantity());
        assertEquals(1002L, response.getProductId());
        assertEquals(1, response.getReservedFromBatchIds().size());

        verify(inventoryClient).getInventory(1002L);
        verify(orderRepository).save(any(Order.class));
    }
}

*/
