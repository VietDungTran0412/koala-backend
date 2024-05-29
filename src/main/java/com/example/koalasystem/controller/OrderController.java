package com.example.koalasystem.controller;

import com.example.koalasystem.dto.CreateOrderDto;
import com.example.koalasystem.dto.OrderDto;
import com.example.koalasystem.dto.RestResponseDto;
import com.example.koalasystem.entity.Order;
import com.example.koalasystem.enums.NormalOrderStatus;
import com.example.koalasystem.mapper.OrderMapper;
import com.example.koalasystem.service.OrderService;
import com.example.koalasystem.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    RestResponseDto createNormalOrder(@RequestBody CreateOrderDto dto) {
        log.info("Create order in koala system");
        orderService.saveOrder(dto, OrderMapper.INSTANCE::toNormalOrder);
        return RestResponseDto.builder().message("Success").build();
    }

    @GetMapping
    RestResponseDto getNormalOrderByStatus(@RequestParam(name = "status") NormalOrderStatus status) {
        log.info("Get order by status within koala system");
        List<OrderDto> orders = orderService.findAllByStatus(status, OrderMapper.INSTANCE::toOrderDto);
        return RestResponseDto.builder().message("Retrieved order").data(orders).build();
    }

}
