package com.example.koalasystem.entity;

import com.example.koalasystem.entity.embedded.OrderItemId;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @MapsId("menuItemId")
    @JoinColumn(name = "menu_item_id", nullable = false, referencedColumnName = "menu_item_id")
    private MenuItem menuItem;

    private Integer quantity;
}
