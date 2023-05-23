package com.example.kafkabasic.api.request;

public record CreateOrderRequestDto(String itemName, int count) {
}
