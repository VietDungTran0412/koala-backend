package com.example.koalasystem.dto;

import com.example.koalasystem.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    @NotNull
    private CustomerDto customer;
    @NotNull
    private Integer numOfPeople;
    @NotBlank
    private String reservationTime;

}
