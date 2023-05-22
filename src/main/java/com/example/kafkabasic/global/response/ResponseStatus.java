package com.example.kafkabasic.global.response;

import org.springframework.http.HttpStatus;

public enum ResponseStatus {
    SUCCESS_ORDER(HttpStatus.OK.value(), "주문 성공");

    private int statusCode;
    private String statusMessage;

    ResponseStatus(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
