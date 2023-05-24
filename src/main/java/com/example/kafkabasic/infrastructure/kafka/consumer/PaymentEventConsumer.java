package com.example.kafkabasic.infrastructure.kafka.consumer;

import com.example.kafkabasic.infrastructure.kafka.event.PaymentProducerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentEventConsumer {

    @KafkaListener(topics = "payment-failed", groupId = "failevent", containerFactory = "kafkaListener")
    public void consumePaymentEvent(String paymentFailEvent) {
        log.info("Consumed message : {}", paymentFailEvent);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String pfe = objectMapper.readValue(paymentFailEvent, String.class);
            PaymentProducerEvent event = objectMapper.readValue(pfe, PaymentProducerEvent.class);
            System.out.println(event.toString());
        } catch (JsonProcessingException e) {
            log.error("Error deserializing PaymentProducerEvent: {}", e.getMessage());
        }
    }
}

