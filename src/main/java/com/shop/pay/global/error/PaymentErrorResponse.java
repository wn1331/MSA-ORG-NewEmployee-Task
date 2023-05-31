package com.shop.pay.global.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentErrorResponse {
    private PaymentErrorCode paymentErrorCode;
    private String errorMessage;
}
