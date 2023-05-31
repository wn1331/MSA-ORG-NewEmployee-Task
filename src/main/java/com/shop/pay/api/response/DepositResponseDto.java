package com.shop.pay.api.response;

import com.shop.pay.domain.bank.BankAccount;

public record DepositResponseDto(Long deposit) {

    public static DepositResponseDto toEntity(BankAccount bankAccount) {
        return new DepositResponseDto(bankAccount.getDeposit());
    }
}
