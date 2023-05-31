package com.shop.pay.api;

import com.shop.pay.api.response.DepositResponseDto;
import com.shop.pay.application.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2/v1/users")
@AllArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("/{memberId}/balance")
    public ResponseEntity<DepositResponseDto> getDepositByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(bankAccountService.getDepositByMemberId(memberId));
    }
}
