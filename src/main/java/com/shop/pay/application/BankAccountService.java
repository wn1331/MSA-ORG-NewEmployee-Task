package com.shop.pay.application;

import com.shop.pay.api.request.BankAccountRequestDto;
import com.shop.pay.domain.bank.BankAccount;
import com.shop.pay.domain.bank.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public void createAccount(BankAccountRequestDto request) {
        BankAccount bankAccount = request.toEntity();
        bankAccountRepository.save(bankAccount);
    }
}
