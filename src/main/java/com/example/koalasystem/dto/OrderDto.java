package com.example.koalasystem.dto;

import com.example.koalasystem.enums.NormalOrderStatus;
import com.example.koalasystem.enums.OrderType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String specialInstruction;
    private Integer table;
    private String date;
    private CustomerDto customer;
    private OrderType orderType;
    private NormalOrderStatus orderStatus;
    private Double totalPrice;

}
