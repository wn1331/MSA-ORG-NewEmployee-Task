package com.shop.order.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.order.domain.order.OrderItem;

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
