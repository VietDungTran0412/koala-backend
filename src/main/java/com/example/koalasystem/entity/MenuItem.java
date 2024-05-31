package com.example.koalasystem.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

// MenuItem entity
@Entity
@Table(name = "menu_item")
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Integer id;
    @Column(name = "item_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @ManyToMany(mappedBy = "menuItems")
    private List<Order> orders;
}
