package com.example.koalasystem.mapper;

import com.example.koalasystem.dto.CreateOrderDto;
import com.example.koalasystem.dto.OrderDto;
import com.example.koalasystem.dto.PaymentResponseDto;
import com.example.koalasystem.entity.NormalOrder;
import com.example.koalasystem.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/* Payment mapper to map the payment entity and the payment dto */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
    PaymentResponseDto toResponseDto(Payment payment);
}

