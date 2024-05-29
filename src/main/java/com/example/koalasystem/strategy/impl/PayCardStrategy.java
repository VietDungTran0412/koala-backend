package com.example.koalasystem.strategy.impl;

import com.example.koalasystem.entity.Order;
import com.example.koalasystem.entity.Payment;
import com.example.koalasystem.strategy.PaymentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Component
public class PayCardStrategy implements PaymentStrategy  {
//    @Override
//    public void pay(Double receivedAmount, Double returnedAmount, Long i) {
//        log.info("Pay by card");
//    }

    @Override
    public Payment pay(Double receivedAmount, Order order) {
        /*
        * @todo: Implement payment method to handle card payment. Only for demo purpose
        * */
        return null;
    }
}
