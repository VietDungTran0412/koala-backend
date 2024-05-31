package com.example.koalasystem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MenuItemCollectionDto {
    @NotNull
    @Valid
    private List<MenuItemDto> menuItems;
}
