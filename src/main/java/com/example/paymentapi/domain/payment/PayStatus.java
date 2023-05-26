package com.example.paymentapi.domain.payment;

import lombok.Getter;

@Getter
public enum PayStatus {
    PENDING("결제 대기"), // 대기 중
    COMPLETED("결제 완료"), // 완료됨
    CANCELED("결제 취소"), // 취소됨
    REFUNDED("환불"),
    FAILED("결제 실패"); // 환불됨

    private String message;

    PayStatus(String message) {
        this.message = message;
    }

    public static boolean isPossiblePay(String message) {
        return PENDING.message.equals(message);
    }
}
