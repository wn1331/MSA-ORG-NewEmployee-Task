package com.shop.pay.global.error.exception;

import com.shop.pay.global.error.PaymentErrorCode;
import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException {

    private PaymentErrorCode paymentErrorCode;
    private String message;

    public PaymentException(PaymentErrorCode paymentErrorCode) {
        super(paymentErrorCode.getMessage());
        this.paymentErrorCode = paymentErrorCode;
        this.message = paymentErrorCode.getMessage();
    }
}
