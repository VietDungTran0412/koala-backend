package com.example.koalasystem.factory;

import com.example.koalasystem.dto.CreateOrderDto;
import com.example.koalasystem.entity.NormalOrder;
import com.example.koalasystem.entity.Order;
import com.example.koalasystem.enums.NormalOrderStatus;
import com.example.koalasystem.enums.OrderType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class OrderFactory {
    public NormalOrder createNormalOrder(CreateOrderDto dto, Function<CreateOrderDto, NormalOrder> mapper, Double totalPrice) {
        NormalOrder order =  mapper.apply(dto);
        order.setTime(new Date());
        order.setOrderType(OrderType.NORMAL);
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(NormalOrderStatus.UNPAID);
        return order;
    }
}
