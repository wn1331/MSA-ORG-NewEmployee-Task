package com.example.kafkabasic.domain.order;

import com.example.kafkabasic.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.example.kafkabasic.domain.order.OrderPayStatus.*;
import static com.example.kafkabasic.domain.order.OrderStatus.ORDER;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderPayStatus orderPayStatus;

    private Order(OrderItem orderItem) {
        this.status = ORDER;
        this.addOrderItem(orderItem);
        this.orderPayStatus = PENDING;
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


    public void changeSuccessPayment() {
        this.orderPayStatus = COMPLETED;
    }

    public void changeFailedPayment() {
        this.orderPayStatus = FAILED;
    }
}
