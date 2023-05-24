package com.example.paymentapi.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum PaymentErrorCode {
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "PAYMENT_NOT_FOUND"),
    ALREADY_PAID(HttpStatus.BAD_REQUEST.value(), "이미 결제가 완료되었습니다."),//error code 400
    PAYMENT_NOT_PAID(HttpStatus.PAYMENT_REQUIRED.value(), "PAYMENT_NOT_PAID"),
    ALL_READY_EXIST_ORDER(HttpStatus.CONFLICT.value(), "이미 해당 주문이 존재합니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST.value(), "해당 유저가 없습니다"),
    WITHDRAW_AMOUNT_EXCEEDS_LIMIT(HttpStatus.BAD_REQUEST.value(), "출금 한도 초과");

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
