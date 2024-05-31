package com.example.koalasystem.dto;

import com.example.koalasystem.enums.PaymentMethod;
import com.example.koalasystem.enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentResponseDto {
    private Long id;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Double returnedAmount;
    private Double receivedAmount;
    private OrderDto order;
}
