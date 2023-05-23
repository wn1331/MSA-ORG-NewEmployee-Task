package com.example.kafkabasic.api.response;

public record OrderResponseDto(Long orderId, String itemName, int count, int totalPrice) {
}
