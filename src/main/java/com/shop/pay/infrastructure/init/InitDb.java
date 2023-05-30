
package com.shop.pay.infrastructure.init;

import com.shop.pay.api.request.BankAccountRequestDto;
import com.shop.pay.application.BankAccountService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final BankAccountService bankAccountService;

    @PostConstruct
    public void init() {
        createBankAccount();
    }

    private void createBankAccount() {
        BankAccountRequestDto request = new BankAccountRequestDto(1L, 100000L);
        bankAccountService.createAccount(request);
    }

}
