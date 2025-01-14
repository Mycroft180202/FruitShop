package com.example.fruit_api.dto;

import com.example.fruit_api.models.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private Order.OrderStatus status;
    private List<OrderDetailDto> orderDetails = new ArrayList<>();

    @Data
    public static class OrderDetailDto {
        private Long orderDetailId;
        private Long productId;
        private String productName;
        private int quantity;
        private BigDecimal unitPrice;

        // Constructors, Getters, and Setters
        public OrderDetailDto() {}

        public OrderDetailDto(Long orderDetailId, Long productId, String productName, int quantity, BigDecimal unitPrice) {
            this.orderDetailId = orderDetailId;
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
    }
}