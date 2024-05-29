package com.example.koalasystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {
    @NotBlank
    private String name;
    private String email;
    @NotBlank
    private String phone;
}
