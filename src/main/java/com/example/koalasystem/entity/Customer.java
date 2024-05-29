package com.example.koalasystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Customer {
    private String name;
    private String phone;
    private String email;
}
