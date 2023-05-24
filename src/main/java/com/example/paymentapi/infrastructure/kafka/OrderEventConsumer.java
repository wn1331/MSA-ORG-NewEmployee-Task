package com.example.paymentapi.infrastructure.kafka;

import com.example.paymentapi.application.OrderPaymentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventConsumer {

    @KafkaListener(topics = "order-payment-topic", groupId = "foo", containerFactory = "kafkaListener")
    public void consumePaymentEvent(String orderPaymentEvent) {
        log.info("Consumed message : {}", orderPaymentEvent);

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String ope = objectMapper.readValue(orderPaymentEvent, String.class);
            OrderPaymentEvent event = objectMapper.readValue(ope,OrderPaymentEvent.class);
            System.out.println(event.toString());
        } catch (JsonProcessingException e) {
            // 역직렬화 오류 처리 로직을 추가합니다.
            log.error("Error deserializing OrderPaymentEvent: {}", e.getMessage());
        }
    }

}
