package com.example.koalasystem.entity;

import com.example.koalasystem.enums.PaymentMethod;
import com.example.koalasystem.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    @Column(name = "returned_amount")
    private Double returnedAmount;
    @Column(name = "received_amount")
    private Double receivedAmount;
    @OneToOne(mappedBy = "payment")
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;
}
