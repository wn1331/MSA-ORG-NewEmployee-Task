package com.shop.order.domain.order;

import com.shop.order.domain.BaseEntity;
import com.shop.order.domain.item.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int price;
    private int count;

    private OrderItem(Item item, int count) {
        this.item = item;
        this.price = item.getPrice() * count;
        this.count = count;
        item.removeStock(count);
    }

    public static OrderItem createOrder(Item item, int count) {
        return new OrderItem(item, count);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void returnStock() {
        this.item.rollbackStock(this.count);
    }
}
