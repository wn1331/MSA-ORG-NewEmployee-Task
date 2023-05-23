package com.example.kafkabasic.infrastructure.kafka.consumer;

import com.example.kafkabasic.application.event.OrderPaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentEventConsumer {

    @KafkaListener(topics = "order-payment-topic", groupId = "foo", containerFactory = "kafkaListener")
    public void consumePaymentEvent(OrderPaymentEvent orderPaymentEvent) {
        log.info("Consumed message : {}", orderPaymentEvent);
    }
}
