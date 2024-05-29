package com.example.koalasystem.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class OrderItemId implements Serializable {
    private Long orderId;
    private Integer menuItemId;
}
