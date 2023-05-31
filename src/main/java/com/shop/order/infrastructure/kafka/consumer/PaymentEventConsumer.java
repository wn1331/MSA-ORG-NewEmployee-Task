package com.shop.order.infrastructure.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.order.application.OrderService;
import com.shop.order.infrastructure.kafka.event.OrderConsumerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "payment-failed", groupId = "failedEvent", containerFactory = "kafkaListener")
    public void paymentFailedEvent(String paymentFailEvent) {
        log.info("결제 실패 이벤트 메시지 : {}", paymentFailEvent);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String pfe = objectMapper.readValue(paymentFailEvent, String.class);
            OrderConsumerEvent event = objectMapper.readValue(pfe, OrderConsumerEvent.class);

            orderService.rollbackOrderTransaction(event); // 주문 보상 트랜잭션
        } catch (JsonProcessingException e) {
            log.error("Error deserializing PaymentProducerEvent: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "payment-success", groupId = "successEvent", containerFactory = "kafkaListener")
    public void paymentSuccessEvent(String paymentSuccessEvent) {
        log.info("결제 성공 이벤트 메시지 : {}", paymentSuccessEvent);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String pfe = objectMapper.readValue(paymentSuccessEvent, String.class);
            OrderConsumerEvent event = objectMapper.readValue(pfe, OrderConsumerEvent.class);

            orderService.successPayment(event);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing PaymentProducerEvent: {}", e.getMessage());
        }
    }
}

