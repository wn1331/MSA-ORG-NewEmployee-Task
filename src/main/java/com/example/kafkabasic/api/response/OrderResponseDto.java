package com.example.kafkabasic.api.response;

import com.example.kafkabasic.domain.order.OrderItem;

public record OrderResponseDto(Long orderId, String itemName, int count, int totalPrice) {
    public static OrderResponseDto toDto(OrderItem orderItem) {
        return new OrderResponseDto(orderItem.getOrder().getId(), orderItem.getItem().getName(), orderItem.getCount(), orderItem.getPrice());
    }
}
