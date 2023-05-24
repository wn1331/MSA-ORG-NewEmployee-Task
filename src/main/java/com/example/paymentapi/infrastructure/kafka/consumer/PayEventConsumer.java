package com.example.paymentapi.infrastructure.kafka.consumer;

import com.example.paymentapi.infrastructure.kafka.event.PaymentConsumerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PayEventConsumer {

    @KafkaListener(topics = "order-payment-topic", groupId = "foo", containerFactory = "kafkaListener")
    public void consumePaymentEvent(String orderProducerEvent) {
        log.info("Consumed message : {}", orderProducerEvent);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String ope = objectMapper.readValue(orderProducerEvent, String.class);
            PaymentConsumerEvent event = objectMapper.readValue(ope, PaymentConsumerEvent.class);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing OrderPaymentEvent: {}", e.getMessage());
        }
    }

}
