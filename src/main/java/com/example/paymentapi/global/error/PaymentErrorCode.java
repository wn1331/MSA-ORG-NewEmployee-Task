package com.example.paymentapi.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum PaymentErrorCode {
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"PAYMENT_NOT_FOUND"),
    PAYMENT_ALREADY_PAID(HttpStatus.BAD_REQUEST.value(),"PAYMENT_ALREADY_PAID"),//error code 400
    PAYMENT_NOT_PAID(HttpStatus.PAYMENT_REQUIRED.value(),"PAYMENT_NOT_PAID");//클라이언트가 결제할 때까지 요청된 콘텐츠를 사용할 수 없음.

    private int errorCode;
    private String errorMessage;

    PaymentErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
