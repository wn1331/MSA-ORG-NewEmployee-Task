package com.example.paymentapi.api.request;

public record CreatePaymentRequestDto(Long orderId, String itemName, int totalPrice) {
}
