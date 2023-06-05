package com.shop.pay.application;

import com.shop.pay.api.request.BankAccountRequestDto;
import com.shop.pay.api.response.DepositResponseDto;
import com.shop.pay.domain.bank.BankAccount;
import com.shop.pay.domain.bank.BankAccountRepository;
import com.shop.pay.global.error.PaymentErrorCode;
import com.shop.pay.global.error.exception.PaymentException;
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

    public DepositResponseDto getDepositByMemberId(Long memberId) {
        BankAccount bankAccount = findBankAccountByMemberId(memberId);
        return DepositResponseDto.toEntity(bankAccount);
    }

    private BankAccount findBankAccountByMemberId(Long memberId) {
        return bankAccountRepository.findByMemberId(memberId).orElseThrow(() -> new PaymentException(PaymentErrorCode.NOT_FOUND_USER));
    }
}
