package com.example.koalasystem.service;

import com.example.koalasystem.dto.CreateOrderDto;
import com.example.koalasystem.dto.OrderDto;
import com.example.koalasystem.entity.MenuItem;
import com.example.koalasystem.entity.NormalOrder;
import com.example.koalasystem.entity.Order;
import com.example.koalasystem.enums.NormalOrderStatus;
import com.example.koalasystem.exception.EntityNotFoundException;
import com.example.koalasystem.factory.OrderFactory;
import com.example.koalasystem.repository.NormalOrderRepository;
import com.example.koalasystem.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private final NormalOrderRepository normalOrderRepository;
    private final MenuService menuService;
    private final OrderFactory orderFactory;

    public OrderService(NormalOrderRepository orderRepository, MenuService menuService, OrderFactory orderFactory) {
        this.normalOrderRepository = orderRepository;
        this.menuService = menuService;
        this.orderFactory = orderFactory;
    }

    private Double calculateTotalPrice(Iterable<Integer> ids, Iterable<MenuItem> sellItems) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer id : ids) {
            if(map.containsKey(id)) {
                map.put(id, map.get(id) + 1);
            }else {
                map.put(id, 1);
            }
        }
        Double res = 0D;
        for (MenuItem item : sellItems) {
//            res = total
            res += map.get(item.getId()) * item.getPrice();
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(CreateOrderDto dto, Function<CreateOrderDto, NormalOrder> mapper) {
        log.info("Order Service start saving order into Koala system");
        List<MenuItem> items = menuService.findByIds(dto.getMenuItemIds());
        Double totalPrice = calculateTotalPrice(dto.getMenuItemIds(), items);
        NormalOrder savedOrder = orderFactory.createNormalOrder(dto, mapper, totalPrice);
        savedOrder.setMenuItems(items);
        normalOrderRepository.save(savedOrder);
    }

    @Transactional(rollbackFor = Exception.class)
    public Order save(NormalOrder order) {
        return normalOrderRepository.save(order);
    }

    public List<OrderDto> findAllByStatus(NormalOrderStatus status, Function<NormalOrder, OrderDto> mapper) {
        log.info("Order Service start receiving order with status {}", status.name());
        List<NormalOrder> orders = normalOrderRepository.findAllByOrderStatus(status);
        List<OrderDto> returnedOrders = orders.stream().map(mapper).collect(Collectors.toList());
        return returnedOrders;
    }

    public NormalOrder getNormalOrderById(Long id) {
        log.info("Getting Normal Order by id: {}", id);
        Optional<NormalOrder> wrapper = normalOrderRepository.findById(id);
        if(!wrapper.isPresent()) throw new EntityNotFoundException("Normal order has not been found!");
        return wrapper.get();
    }
}
