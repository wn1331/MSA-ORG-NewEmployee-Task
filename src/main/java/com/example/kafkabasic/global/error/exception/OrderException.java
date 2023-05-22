package com.example.kafkabasic.global.error.exception;

import com.example.kafkabasic.global.error.OrderErrorCode;
import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {
    private OrderErrorCode orderErrorCode;
    private String message;

    public OrderException(OrderErrorCode orderErrorCode) {
        super(orderErrorCode.getMessage());
        this.orderErrorCode = orderErrorCode;
        this.message = orderErrorCode.getMessage();
    }
}

