package com.example.paymentapi.global.error.exception;

import com.example.paymentapi.global.error.PaymentErrorCode;
import lombok.Getter;


@Getter
public class PaymentException extends RuntimeException{

    private PaymentErrorCode paymentErrorCode;
    private String message;

    public PaymentException(PaymentErrorCode paymentErrorCode) {
        super(paymentErrorCode.getMessage());
        this.paymentErrorCode = paymentErrorCode;
        this.message = paymentErrorCode.getMessage();
    }
}
