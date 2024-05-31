package com.example.koalasystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationDto {
    @NotNull
    private CustomerDto customer;
    @NotNull
    private Integer numOfPeople;
    @NotBlank
    private String reservationTime;

}
