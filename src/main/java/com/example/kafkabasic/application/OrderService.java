package com.example.kafkabasic.application;

import com.example.kafkabasic.api.request.CreateOrderRequestDto;
import com.example.kafkabasic.api.response.OrderResponseDto;
import com.example.kafkabasic.application.event.OrderPaymentEvent;
import com.example.kafkabasic.domain.item.Item;
import com.example.kafkabasic.domain.item.ItemRepository;
import com.example.kafkabasic.domain.order.Order;
import com.example.kafkabasic.domain.order.OrderItem;
import com.example.kafkabasic.domain.order.OrderItemRepository;
import com.example.kafkabasic.domain.order.OrderRepository;
import com.example.kafkabasic.global.error.exception.OrderException;
import com.example.kafkabasic.infrastructure.kafka.producer.PaymentProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kafkabasic.global.error.OrderErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentProducerService paymentProducerService;

    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequestDto request) {
        Item item = itemRepository.findByName(request.orderName()).orElseThrow(() -> new OrderException(NOT_FOUND));

        OrderItem orderItem = OrderItem.createOrder(item, request.count());
        orderItemRepository.save(orderItem);

        Order order = Order.createOrder(orderItem);
        orderRepository.save(order);

        // kafKa
        OrderPaymentEvent paymentEvent = new OrderPaymentEvent(order.getId(), orderItem.getPrice());
        paymentProducerService.send("order-payment-topic", paymentEvent);

        // orderItem -> 주문서
        return new OrderResponseDto(order.getId(), orderItem.getItem().getName(), orderItem.getCount(), orderItem.getPrice());
    }
}
