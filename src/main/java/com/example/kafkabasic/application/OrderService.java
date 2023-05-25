package com.example.kafkabasic.application;

import com.example.kafkabasic.api.request.CreateOrderRequestDto;
import com.example.kafkabasic.api.response.OrderResponseDto;
import com.example.kafkabasic.domain.item.Item;
import com.example.kafkabasic.domain.item.ItemRepository;
import com.example.kafkabasic.domain.order.Order;
import com.example.kafkabasic.domain.order.OrderItem;
import com.example.kafkabasic.domain.order.OrderItemRepository;
import com.example.kafkabasic.domain.order.OrderRepository;
import com.example.kafkabasic.global.error.exception.OrderException;
import com.example.kafkabasic.infrastructure.kafka.event.OrderConsumerEvent;
import com.example.kafkabasic.infrastructure.kafka.event.OrderProducerEvent;
import com.example.kafkabasic.infrastructure.kafka.producer.OrderKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.kafkabasic.global.error.OrderErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderKafkaProducer paymentKafkaProducer;

    // 주문 생성과 관련된 작업
    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequestDto request) {
        Item item = itemRepository.findByName(request.itemName()).orElseThrow(() -> new OrderException(NOT_FOUND));

        OrderItem orderItem = OrderItem.createOrder(item, request.count());
        orderItemRepository.save(orderItem);

        Order order = Order.createOrder(orderItem);
        orderRepository.save(order);

        sendOrderEvent(order, orderItem); // 주문 완료 kafka message

        return OrderResponseDto.toDto(orderItem);
    }

    private void sendOrderEvent(Order order, OrderItem orderItem) {
        OrderProducerEvent paymentEvent = new OrderProducerEvent(order.getId(), orderItem.getPrice());
        paymentKafkaProducer.send("order-payment-topic", paymentEvent);
    }

    @Transactional
    public void orderRollbackTransaction(OrderConsumerEvent event) {
        Long orderId = event.orderId();
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        rollbackStock(orderItems);
        deleteOrder(orderId);
    }

    private void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    private void rollbackStock(List<OrderItem> orderItems) {
        orderItems.forEach(orderItem -> {
            int itemCount = orderItem.getCount();
            Item item = orderItem.getItem();
            item.rollbackStock(itemCount);
        });
    }
}
