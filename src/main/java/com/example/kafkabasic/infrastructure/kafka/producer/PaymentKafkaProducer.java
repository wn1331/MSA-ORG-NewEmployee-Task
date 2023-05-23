package com.example.kafkabasic.infrastructure.kafka.producer;

import com.example.kafkabasic.application.event.OrderPaymentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, OrderPaymentEvent orderPaymentEvent) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = objectMapper.writeValueAsString(orderPaymentEvent);
        kafkaTemplate.send(topic, jsonInString);

        log.info("Kafka: {} 메시지 발송 성공" + orderPaymentEvent);
    }

}
