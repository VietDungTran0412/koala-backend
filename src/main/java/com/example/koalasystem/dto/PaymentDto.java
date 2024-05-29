package com.example.koalasystem.dto;

import com.example.koalasystem.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDto {
    @NotNull
    private Double receivedAmount;
    private PaymentMethod paymentType;
}
