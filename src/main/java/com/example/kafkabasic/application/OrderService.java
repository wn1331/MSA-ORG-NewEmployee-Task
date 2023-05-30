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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.kafkabasic.global.error.OrderErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // 주문 생성
    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequestDto request) {
        Item item = itemRepository.findByName(request.itemName())
                .orElseThrow(() -> new OrderException(NOT_FOUND));

        OrderItem orderItem = createOrderItem(request, item);
        createOrder(orderItem);

        return OrderResponseDto.toDto(orderItem);
    }

    private void createOrder(OrderItem orderItem) {
        Order order = Order.createOrder(orderItem);
        orderRepository.save(order);
    }

    private OrderItem createOrderItem(CreateOrderRequestDto request, Item item) {
        OrderItem orderItem = OrderItem.createOrder(item, request.count());
        return orderItemRepository.save(orderItem);
    }

    // 주문 rollback
    @Transactional
    public void rollbackOrderTransaction(OrderConsumerEvent event) {
        Order order = findOrderById(event.orderId());
        order.changeFailedPayment();
        rollbackStock(order.getItems());
    }

    private void rollbackStock(List<OrderItem> orderItems) {
        orderItems.forEach(OrderItem::returnStock);
    }

    @Transactional
    public void successPayment(OrderConsumerEvent event) {
        Order order = findOrderById(event.orderId());
        order.changeSuccessPayment();
    }

    private Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(NOT_FOUND));
    }

    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }
}
