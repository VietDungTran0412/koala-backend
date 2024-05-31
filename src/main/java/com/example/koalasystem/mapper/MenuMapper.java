package com.example.koalasystem.mapper;

import com.example.koalasystem.dto.MenuItemDto;
import com.example.koalasystem.entity.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// Mapper to map the Dto object to MenuItem entity
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);
    MenuItem toMenuItem(MenuItemDto dto);
    MenuItemDto toMenuItemDto(MenuItem item);
}
