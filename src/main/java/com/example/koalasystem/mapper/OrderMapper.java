package com.example.koalasystem.mapper;

import com.example.koalasystem.dto.CreateOrderDto;
import com.example.koalasystem.dto.OrderDto;
import com.example.koalasystem.entity.NormalOrder;
import com.example.koalasystem.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    NormalOrder toNormalOrder(CreateOrderDto dto);
    @Mapping(source = "time", target = "date")
    OrderDto toOrderDto(NormalOrder normalOrder);
}
