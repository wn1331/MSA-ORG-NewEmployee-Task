package com.shop.order.api.request;

import com.shop.order.domain.item.Item;

public record CreateItemRequestDto(String name, int price, int stockQuantity) {

    public Item toEntity() {
        return Item.createItem(name, price, stockQuantity);
    }
}
