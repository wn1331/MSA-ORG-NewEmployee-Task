package com.example.kafkabasic.domain.item;

import com.example.kafkabasic.domain.BaseEntity;
import com.example.kafkabasic.global.error.exception.OrderException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.kafkabasic.global.error.OrderErrorCode.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public static Item createItem(String name, int price, int stockQuantity) {
        return new Item(name, price, stockQuantity);
    }

    public void removeStock(int count) {
        if (this.stockQuantity < count) {
            throw new OrderException(NOT_ENOUGH);
        }
        this.stockQuantity -= count;
    }
}
