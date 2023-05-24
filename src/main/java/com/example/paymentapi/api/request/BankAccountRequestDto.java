package com.example.paymentapi.api.request;

import com.example.paymentapi.domain.bank.BankAccount;

public record BankAccountRequestDto(Long memberId, Long deposit) {
    public BankAccount toEntity() {
        return BankAccount.createAccount(memberId, deposit);
    }
}
