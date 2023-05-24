package com.example.kafkabasic.infrastructure.kafka.producer;

import com.example.kafkabasic.infrastructure.kafka.event.OrderProducerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, OrderProducerEvent orderPaymentEvent) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(orderPaymentEvent));
            log.info("Kafka: {} 메시지 발송 성공", orderPaymentEvent);
        } catch (JsonProcessingException e) {
            log.info("KafKa 메시지 발송 실패");
            throw new RuntimeException(e);
        }
    }

}
