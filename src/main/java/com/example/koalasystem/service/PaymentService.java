package com.example.koalasystem.service;

import com.example.koalasystem.entity.NormalOrder;
import com.example.koalasystem.entity.Order;
import com.example.koalasystem.entity.Payment;
import com.example.koalasystem.enums.NormalOrderStatus;
import com.example.koalasystem.enums.PaymentMethod;
import com.example.koalasystem.exception.PaymentException;
import com.example.koalasystem.strategy.PaymentStrategy;
import com.example.koalasystem.strategy.impl.PayCardStrategy;
import com.example.koalasystem.strategy.impl.PayCashStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    private final Map<PaymentMethod, PaymentStrategy> paymentStrategies;
    private final OrderService orderService;

    public PaymentService(PayCardStrategy payCardStrategy, PayCashStrategy payCashStrategy, OrderService orderService) {
        this.paymentStrategies = new HashMap<>();
        this.paymentStrategies.put(PaymentMethod.CASH, payCashStrategy);
        this.paymentStrategies.put(PaymentMethod.CREDIT_CARD, payCardStrategy);
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Payment processPayment(PaymentMethod paymentType, Double receivedAmount, Long orderId) {
        PaymentStrategy strategy = paymentStrategies.get(paymentType);
        NormalOrder order = orderService.getNormalOrderById(orderId);
        if(order.getOrderStatus() == NormalOrderStatus.PAID) {
            throw new PaymentException("Order has been paid");
        }
        if (strategy != null) {
            Payment payment = strategy.pay(receivedAmount, order);
            order.setOrderStatus(NormalOrderStatus.PAID);
            orderService.save(order);
            return payment;
        } else {
            throw new IllegalArgumentException("Invalid payment type: " + paymentType);
        }
    }
}
