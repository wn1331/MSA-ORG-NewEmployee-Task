package com.shop.pay.infrastructure.kafka.event;

public record PaymentProducerEvent(Long orderId) {
}
