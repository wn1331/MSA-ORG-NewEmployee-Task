package com.example.kafkabasic.infrastructure.kafka.consumer;

import com.example.kafkabasic.infrastructure.kafka.event.OrderConsumerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventConsumer {

    @KafkaListener(topics = "order-payment-topic", groupId = "foo", containerFactory = "kafkaListener")
    public void consumePaymentEvent(String paymentProducerEvent) {
        log.info("Consumed message : {}", paymentProducerEvent);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String ope = objectMapper.readValue(paymentProducerEvent, String.class);
            OrderConsumerEvent event = objectMapper.readValue(ope, OrderConsumerEvent.class);
            System.out.println(event.toString());
        } catch (JsonProcessingException e) {
            log.error("Error deserializing OrderPaymentEvent: {}", e.getMessage());
        }
    }
}

