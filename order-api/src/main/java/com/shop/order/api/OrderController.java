package com.shop.order.api;

import com.shop.order.api.request.CreateOrderRequestDto;
import com.shop.order.api.response.OrderResponseDto;
import com.shop.order.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api1/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.createOrder(requestDto));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrderItems() {
        return ResponseEntity.ok(orderService.getOrderItems());
    }
}