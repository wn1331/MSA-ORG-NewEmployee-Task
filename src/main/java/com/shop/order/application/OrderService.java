package com.shop.order.application;

import com.shop.order.api.request.CreateOrderRequestDto;
import com.shop.order.api.response.OrderResponseDto;
import com.shop.order.domain.item.Item;
import com.shop.order.domain.item.ItemRepository;
import com.shop.order.domain.order.Order;
import com.shop.order.domain.order.OrderItem;
import com.shop.order.domain.order.OrderItemRepository;
import com.shop.order.domain.order.OrderRepository;
import com.shop.order.global.error.exception.OrderException;
import com.shop.order.infrastructure.kafka.event.OrderConsumerEvent;
import com.shop.order.global.error.OrderErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .orElseThrow(() -> new OrderException(OrderErrorCode.NOT_FOUND));

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
                .orElseThrow(() -> new OrderException(OrderErrorCode.NOT_FOUND));
    }

    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }
}
