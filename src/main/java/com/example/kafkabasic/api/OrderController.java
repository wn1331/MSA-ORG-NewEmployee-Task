package com.example.kafkabasic.api;

import com.example.kafkabasic.api.request.CreateOrderRequestDto;
import com.example.kafkabasic.api.response.OrderResponseDto;
import com.example.kafkabasic.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/send")
    public OrderResponseDto createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        return orderService.createOrder(requestDto);
    }
}
