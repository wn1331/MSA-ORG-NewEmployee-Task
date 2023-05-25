package com.example.kafkabasic.domain.order;

import com.example.kafkabasic.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    private Order(OrderItem orderItem) {
        this.status = OrderStatus.ORDER;
        this.addOrderItem(orderItem);
    }

    private void addOrderItem(OrderItem orderItem) {
        this.items.add(orderItem);
        if (orderItem.getOrder() != this) {
            orderItem.setOrder(this);
        }
    }

    public static Order createOrder(OrderItem orderItem) {
        return new Order(orderItem);
    }


}
