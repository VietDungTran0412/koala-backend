package com.example.koalasystem.dto;

import com.example.koalasystem.enums.OrderType;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    private CustomerDto customer;
    private String deliveryId;
    private String specialInstruction;
    private List<Integer> menuItemIds;
    private OrderType orderType;
    private Integer table;
}
