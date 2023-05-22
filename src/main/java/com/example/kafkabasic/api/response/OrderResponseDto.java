package com.example.kafkabasic.api.response;

import com.example.kafkabasic.global.response.ResponseStatus;

public record OrderResponseDto(Long orderId,String itemName,int count ,int totalPrice) {


}
