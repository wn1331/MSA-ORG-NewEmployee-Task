package com.example.kafkabasic.api.response;

import com.example.kafkabasic.global.response.ResponseStatus;

public record OrderResponseDto(int statusCode, String statusMessage) {

    public OrderResponseDto(ResponseStatus responseStatus) {
        this(responseStatus.getStatusCode(), responseStatus.getStatusMessage());
    }
}
