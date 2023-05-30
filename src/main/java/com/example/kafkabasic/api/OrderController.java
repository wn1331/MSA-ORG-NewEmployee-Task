package com.example.kafkabasic.api;

import com.example.kafkabasic.api.request.CreateOrderRequestDto;
import com.example.kafkabasic.api.response.OrderResponseDto;
import com.example.kafkabasic.application.OrderService;
import com.example.kafkabasic.domain.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.kafkabasic.api.response.OrderResponseDto.toDto;

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
        List<OrderResponseDto> response = orderService.getOrderItems().stream()
                .map(OrderResponseDto::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}