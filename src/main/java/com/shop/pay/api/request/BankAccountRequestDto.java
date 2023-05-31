package com.shop.pay.api.request;

import com.shop.pay.domain.bank.BankAccount;

public record BankAccountRequestDto(Long memberId, Long deposit) {
    public BankAccount toEntity() {
        return BankAccount.createAccount(memberId, deposit);
    }
}
