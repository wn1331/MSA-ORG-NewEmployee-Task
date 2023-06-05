package com.shop.pay.api.request;

public record CreatePaymentRequestDto(Long orderId, int totalPrice, String payStatus) {
}
