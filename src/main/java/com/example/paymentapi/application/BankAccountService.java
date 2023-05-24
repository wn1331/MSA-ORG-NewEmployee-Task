package com.example.paymentapi.application;

import com.example.paymentapi.api.request.BankAccountRequestDto;
import com.example.paymentapi.domain.bank.BankAccount;
import com.example.paymentapi.domain.bank.BankAccountRepository;
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
