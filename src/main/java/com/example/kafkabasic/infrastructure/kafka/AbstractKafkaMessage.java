package com.example.kafkabasic.infrastructure.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractKafkaMessage<V> {

    private final KafkaTemplate<String, V> kafkaTemplate;

    public void send(String topic, V v) {
        kafkaTemplate.send(topic, v);
        log.info("topic: {}, evert: {} 발송 성공", topic, v);
    }
}
