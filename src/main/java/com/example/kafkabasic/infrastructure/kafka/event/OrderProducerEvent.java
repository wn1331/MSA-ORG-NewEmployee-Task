package com.example.kafkabasic.infrastructure.kafka.event;

public record OrderProducerEvent(Long orderId, int price) {
}
