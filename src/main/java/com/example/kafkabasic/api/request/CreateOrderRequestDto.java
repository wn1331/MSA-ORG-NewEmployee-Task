package com.example.kafkabasic.api.request;

public record CreateOrderRequestDto(String orderName, int count) {
}
