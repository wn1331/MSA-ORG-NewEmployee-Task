package com.shop.pay.api;

import com.shop.pay.api.request.CreatePaymentRequestDto;
import com.shop.pay.api.response.PaymentResponseDto;
import com.shop.pay.application.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2/v1")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody CreatePaymentRequestDto request) {
        return ResponseEntity.ok(paymentService.processPayment(request));
    }
}
