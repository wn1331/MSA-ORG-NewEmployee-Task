package com.example.paymentapi.api;

import com.example.paymentapi.api.request.CreatePaymentRequestDto;
import com.example.paymentapi.api.response.PaymentResponseDto;
import com.example.paymentapi.application.PaymentService;
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
        return ResponseEntity.ok(paymentService.paymentProcess(request));
    }
}
