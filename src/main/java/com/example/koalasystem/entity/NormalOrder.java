package com.example.koalasystem.entity;

import com.example.koalasystem.enums.NormalOrderStatus;
import jakarta.persistence.*;
import lombok.Data;


/* NormalOrder entity derived from abstract Order */
@Entity
@jakarta.persistence.Table(name = "koala_normal_order")
@Data
@PrimaryKeyJoinColumn(name = "normal_order_id")
public class NormalOrder extends Order {
    @Column(name = "order_table")
    private Integer table;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private NormalOrderStatus orderStatus;
}
