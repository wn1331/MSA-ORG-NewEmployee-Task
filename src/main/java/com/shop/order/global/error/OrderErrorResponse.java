package com.shop.order.global.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderErrorResponse {
    private OrderErrorCode orderErrorCode;
    private String errorMessage;
}
