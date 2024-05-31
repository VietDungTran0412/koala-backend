package com.example.koalasystem.service;

import com.example.koalasystem.dto.MenuItemDto;
import com.example.koalasystem.dto.MenuItemCollectionDto;
import com.example.koalasystem.entity.MenuItem;
import com.example.koalasystem.exception.EntityNotFoundException;
import com.example.koalasystem.repository.MenuItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/* Handles Menu relevant business logic and perform CRUD operations */
@Service
@Slf4j
public class MenuService {
    private final MenuItemRepository repository;
    public MenuService(MenuItemRepository repository) {
        this.repository = repository;
    }

    /* Create new menu item */
    @Transactional(rollbackFor = Exception.class)
    public List<MenuItemDto> create(MenuItemCollectionDto dto, Function<MenuItemDto, MenuItem> toEntity, Function<MenuItem, MenuItemDto> toDto) {
        List<MenuItem> menuItems = dto.getMenuItems().stream().map(toEntity).collect(Collectors.toList());
        log.info("Saving menu items into koala");
        List<MenuItem> savedMenuItems = repository.saveAll(menuItems);
        return savedMenuItems.stream().map(toDto).collect(Collectors.toList());
    }

    /* Retrieve all the menu item */
    public List<MenuItemDto> getAll(Function<MenuItem, MenuItemDto> toDto) {
        log.info("Retrieving menu from koala system");
        return repository.findAll().stream().map(toDto).collect(Collectors.toList());
    }

    /* Find menu item by ids */
    public List<MenuItem> findByIds(Iterable<Integer> ids) {
        log.info("Retrieving menu item by list of ids");
        return repository.findAllById(ids);
    }

    /* Delete menu item by id */
    @Transactional(rollbackFor = Exception.class)
    public void deleteItemById(Integer id) {
        Optional<MenuItem> menuItemWrapper = repository.findById(id);
        if(!menuItemWrapper.isPresent()) {
            log.error("Menu item is not found with id: {}", id);
            throw new EntityNotFoundException("Menu item with id " + id + " has not been found.");
        }
        repository.deleteById(id);
    }

    public MenuItemDto updateItemById(Integer id, MenuItemDto dto, Function<MenuItem, MenuItemDto> toDto) {
        Optional<MenuItem> menuItemWrapper = repository.findById(id);
        if(!menuItemWrapper.isPresent()) {
            log.error("Menu item is not found with id: {}", id);
            throw new EntityNotFoundException("Menu item with id " + id + " has not been found.");
        }
        MenuItem updatedItem = menuItemWrapper.get();
        updatedItem.setDescription(dto.getDescription());
        updatedItem.setPrice(dto.getPrice());
        updatedItem.setName(dto.getName());
        repository.save(updatedItem);
        return toDto.apply(updatedItem);
    }
}
