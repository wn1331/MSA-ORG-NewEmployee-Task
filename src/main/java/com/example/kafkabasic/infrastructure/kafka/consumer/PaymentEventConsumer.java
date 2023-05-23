package com.example.kafkabasic.infrastructure.kafka.consumer;

import com.example.kafkabasic.application.event.OrderPaymentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class PaymentEventConsumer {

    @KafkaListener(topics = "order-payment-topic", groupId = "foo", containerFactory = "kafkaListener")
    public void consumePaymentEvent(String orderPaymentEvent) {
        log.info("Consumed message : {}", orderPaymentEvent);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
        try {
            OrderPaymentEvent event = objectMapper.readValue(orderPaymentEvent, OrderPaymentEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
