package com.example.paymentapi.api.request;

public record CreatePaymentRequestDto(Long orderId, int totalPrice, String payStatus) {
}
