package com.example.koalasystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/* Data transfer object for customer */
@Data
public class CustomerDto {
    @NotBlank
    private String name;
    private String email;
    @NotBlank
    @Pattern(regexp = "\\d+", message = "Field must contain only numbers")
    private String phone;
}
