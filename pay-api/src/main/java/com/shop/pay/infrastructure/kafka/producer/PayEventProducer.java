package com.shop.pay.infrastructure.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.pay.infrastructure.kafka.event.PaymentProducerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PayEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, PaymentProducerEvent paymentEventMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(paymentEventMessage));
            log.info("Kafka: {} 메시지 발송 성공", paymentEventMessage);
        } catch (JsonProcessingException e) {
            log.info("Kafka 메시지 발송 실패");
            throw new RuntimeException(e);
        }

    }
}
