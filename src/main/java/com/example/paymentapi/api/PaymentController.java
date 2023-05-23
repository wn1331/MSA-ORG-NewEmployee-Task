package com.example.paymentapi.api;

import com.example.paymentapi.api.request.CreatePaymentRequestDto;
import com.example.paymentapi.api.response.PaymentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2/v1")
public class PaymentController {


    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody CreatePaymentRequestDto paymentRequestDto) {
        PaymentResponseDto paymentResponseDto;//서비스추가작성
        System.out.println("api2(8081포트) 인식 완료  : "+paymentRequestDto.toString());
        return ResponseEntity.ok(new PaymentResponseDto());
    }
}
