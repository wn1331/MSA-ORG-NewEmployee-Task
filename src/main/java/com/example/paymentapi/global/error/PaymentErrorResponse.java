package com.example.paymentapi.global.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentErrorResponse {
    private PaymentErrorCode paymentErrorCode;
    private String errorMessage;
}
