package com.korber.order.service;

import com.korber.inventory.dto.BatchResponse;
import com.korber.inventory.dto.InventoryResponse;
import com.korber.order.client.InventoryClient;
import com.korber.order.dto.OrderRequest;
import com.korber.order.dto.OrderResponse;
import com.korber.order.model.Order;
import com.korber.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final InventoryClient inventoryClient;
    private final OrderRepository orderRepository;

    public OrderService(InventoryClient inventoryClient,
                        OrderRepository orderRepository) {
        this.inventoryClient = inventoryClient;
        this.orderRepository = orderRepository;
    }

    public OrderResponse placeOrder(OrderRequest request) {

        InventoryResponse inventory =
                inventoryClient.getInventory(request.getProductId());

        if (inventory == null || inventory.getBatches().isEmpty()) {
            throw new IllegalStateException("Product not available in inventory");
        }

        int totalAvailable = inventory.getBatches()
                .stream()
                .mapToInt(BatchResponse::getQuantity)
                .sum();

        if (totalAvailable < request.getQuantity()) {
            throw new IllegalStateException(
                    "Insufficient inventory. Available: " + totalAvailable);
        }

        int remainingQty = request.getQuantity();
        List<Long> reservedBatchIds = new ArrayList<>();

        for (BatchResponse batch : inventory.getBatches()) {

            if (remainingQty <= 0) {
                break;
            }

            int reserveQty = Math.min(batch.getQuantity(), remainingQty);

            remainingQty -= reserveQty;
            reservedBatchIds.add(batch.getBatchId());
        }

        Order order = new Order();
        order.setOrderId(System.currentTimeMillis());
        order.setProductId(request.getProductId());
        order.setProductName(inventory.getProductName());
        order.setQuantity(request.getQuantity());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        Order savedOrder = orderRepository.save(order);

        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .productId(savedOrder.getProductId())
                .productName(savedOrder.getProductName())
                .quantity(savedOrder.getQuantity())
                .status(savedOrder.getStatus())
                .reservedFromBatchIds(reservedBatchIds)
                .message("Order placed. Inventory reserved.")
                .build();
    }
}

