package com.example.koalasystem.strategy;


import com.example.koalasystem.entity.Order;
import com.example.koalasystem.entity.Payment;

public interface PaymentStrategy {
    Payment pay(Double receivedAmount, Order order);
}
