package com.example.kafkabasic.api.error;

import com.example.kafkabasic.global.error.OrderErrorResponse;
import com.example.kafkabasic.global.error.exception.OrderException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public OrderErrorResponse notFoundItem(HttpServletRequest request, OrderException e) {
        return new OrderErrorResponse(e.getOrderErrorCode(), e.getMessage());
    }
}
