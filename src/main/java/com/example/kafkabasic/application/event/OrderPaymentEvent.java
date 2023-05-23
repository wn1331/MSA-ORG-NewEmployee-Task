package com.example.kafkabasic.application.event;

public record OrderPaymentEvent(Long orderId, int price) {
}
