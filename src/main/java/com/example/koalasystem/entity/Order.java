package com.example.koalasystem.entity;

import com.example.koalasystem.entity.embedded.Customer;
import com.example.koalasystem.enums.OrderType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "koala_order")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "cust_name")),
            @AttributeOverride( name = "phone", column = @Column(name = "cust_phone_number")),
            @AttributeOverride( name = "email", column = @Column(name = "cust_email"))
    })
    private Customer customer;
    @Column(name = "special_instruction")
    private String specialInstruction;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "time")
    private Date time;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderType orderType;
    @OneToOne
    private Payment payment;
    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<MenuItem> menuItems;
}
