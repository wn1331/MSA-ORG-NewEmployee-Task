package com.example.kafkabasic.infrastructure.kafka.consumer;

import com.example.kafkabasic.application.OrderService;
import com.example.kafkabasic.infrastructure.kafka.event.OrderConsumerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "payment-failed", groupId = "failevent", containerFactory = "kafkaListener")
    public void consumePaymentEvent(String paymentFailEvent) {
        log.info("Consumed message : {}", paymentFailEvent);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String pfe = objectMapper.readValue(paymentFailEvent, String.class);
            OrderConsumerEvent event = objectMapper.readValue(pfe, OrderConsumerEvent.class);

            orderService.orderRollbackTransaction(event);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing PaymentProducerEvent: {}", e.getMessage());
        }
    }
}

