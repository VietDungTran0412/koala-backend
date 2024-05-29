package com.example.koalasystem.controller;

import com.example.koalasystem.dto.MenuItemCollectionDto;
import com.example.koalasystem.dto.MenuItemDto;
import com.example.koalasystem.dto.RestResponseDto;
import com.example.koalasystem.entity.MenuItem;
import com.example.koalasystem.mapper.MenuMapper;
import com.example.koalasystem.service.MenuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
* Represents the MenuHandler class in the UML design
* - Validate request from the client
* - Define the URL suffix
* - Handle request to manipulate menu items
* */
@RestController
@RequestMapping("/menu")
@AllArgsConstructor
@Slf4j
public class MenuController {
    private final MenuService menuService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RestResponseDto addMenuItem(@RequestBody @Valid MenuItemCollectionDto menuItemDtos) {
        log.info("Request to add menu item into koala ");
        List<MenuItemDto> res = menuService.create(menuItemDtos, MenuMapper.INSTANCE::toMenuItem, MenuMapper.INSTANCE::toMenuItemDto);
        return RestResponseDto.builder().message("Created new item").data(res).build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    RestResponseDto deleteMenuItem(@PathVariable Integer id) {
        log.info("Request to delete item with id: {}", id);
        menuService.deleteItemById(id);
        return RestResponseDto.builder().message("Successfully deleted").build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    RestResponseDto updateMenuItem(@PathVariable Integer id, @RequestBody @Valid MenuItemDto dto) {
        log.info("Request to edit item with id: {}", id);
        MenuItemDto res = menuService.updateItemById(id, dto, MenuMapper.INSTANCE::toMenuItemDto);
        return RestResponseDto.builder().message("Successfully updated").data(res).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    RestResponseDto getMenuItems() {
        log.info("Request to retrieve menu from koala system");
        List<MenuItemDto> res = menuService.getAll(MenuMapper.INSTANCE::toMenuItemDto);
        return RestResponseDto.builder().message("Retrieved current menu from koala").data(res).build();
    }
}
