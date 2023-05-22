package com.example.kafkabasic.global.error;

import org.springframework.http.HttpStatus;

public enum OrderErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "상품 정보가 없습니다."),
    NOT_ENOUGH(400, "상품 수가 부족합니다.");

    private int errorCode;
    private String errorMessage;

    OrderErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return errorMessage;
    }
}
