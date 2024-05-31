package com.example.koalasystem.dto;

import com.example.koalasystem.enums.OrderType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/* Data Transfer Object for order */
@Data
public class CreateOrderDto {
    @NotNull
    @Valid
    private CustomerDto customer;
    private String deliveryId;
    private String specialInstruction;
    @NotNull
    @Size(min = 1)
    private List<Integer> menuItemIds;
    private OrderType orderType;
    private Integer table;
}
