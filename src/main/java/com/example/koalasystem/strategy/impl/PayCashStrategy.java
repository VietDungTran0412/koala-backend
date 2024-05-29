package com.example.koalasystem.strategy.impl;

import com.example.koalasystem.entity.Order;
import com.example.koalasystem.entity.Payment;
import com.example.koalasystem.enums.PaymentMethod;
import com.example.koalasystem.enums.PaymentStatus;
import com.example.koalasystem.repository.PaymentRepository;
import com.example.koalasystem.strategy.PaymentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class PayCashStrategy implements PaymentStrategy {
    private final PaymentRepository paymentRepository;

    public PayCashStrategy(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Payment pay(Double receivedAmount, Order order) {
        log.info("Pay by cash");
        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.RECEIVED);
        payment.setPaymentMethod(PaymentMethod.CASH);
        payment.setOrder(order);
        payment.setReceivedAmount(receivedAmount);
        Double returnedAmount = receivedAmount - order.getTotalPrice();
        if(returnedAmount < 0) {
            throw new IllegalArgumentException("Received amount is no enough.");
        }
        payment.setReturnedAmount(returnedAmount);
        return paymentRepository.save(payment);
    }
}
