package com.example.kafkabasic.api.request;

import com.example.kafkabasic.domain.item.Item;

public record CreateItemRequestDto(String name, int price, int stockQuantity) {

    public Item toEntity() {
        return Item.createItem(name, price, stockQuantity);
    }
}
