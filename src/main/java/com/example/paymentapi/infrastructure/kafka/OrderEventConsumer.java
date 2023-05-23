package com.example.paymentapi.infrastructure.kafka;

import com.example.paymentapi.application.OrderPaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    @KafkaListener(topics = "order-payment-topic", groupId = "foo", containerFactory = "kafkaListener")
    public void consumeOrderEvent() {

    }

}
