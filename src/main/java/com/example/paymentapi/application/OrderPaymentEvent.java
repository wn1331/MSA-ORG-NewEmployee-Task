package com.example.paymentapi.application;

public record OrderPaymentEvent(Long orderId, int price) {
}
