package com.example.kafkabasic.api.response;

import com.example.kafkabasic.domain.order.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record OrderResponseDto(
        Long orderId,
        String itemName,
        int count,
        int totalPrice,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime orderDate,
        String payStatus
) {
    public static OrderResponseDto toDto(OrderItem orderItem) {
        return new OrderResponseDto(
                orderItem.getOrder().getId(),
                orderItem.getItem().getName(),
                orderItem.getCount(),
                orderItem.getPrice(),
                orderItem.getOrder().getCreateAt(),
                orderItem.getOrder().getOrderPayStatus().getStatus()
        );
    }
}
