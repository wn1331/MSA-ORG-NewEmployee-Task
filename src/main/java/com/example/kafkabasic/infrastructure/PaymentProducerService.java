package com.example.kafkabasic.infrastructure;

import com.example.kafkabasic.application.OrderPaymentEvent;
import com.example.kafkabasic.infrastructure.kafka.AbstractKafkaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducerService extends AbstractKafkaMessage<OrderPaymentEvent> {
    public PaymentProducerService(KafkaTemplate<String, OrderPaymentEvent> kafkaTemplate) {
        super(kafkaTemplate);
    }
}
