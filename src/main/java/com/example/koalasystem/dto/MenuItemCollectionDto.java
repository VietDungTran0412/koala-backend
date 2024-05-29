package com.example.koalasystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuItemCollectionDto {
    private List<MenuItemDto> menuItems;
}
