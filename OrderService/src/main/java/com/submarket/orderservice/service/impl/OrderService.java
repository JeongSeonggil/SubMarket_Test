package com.submarket.orderservice.service.impl;

import com.submarket.orderservice.dto.OrderDto;
import com.submarket.orderservice.mapper.impl.OrderMapper;
import com.submarket.orderservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service(value = "OrderService")
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderMapper orderMapper;

    @Override
    public int insertOrder(OrderDto orderDto) throws Exception {
        log.info(this.getClass().getName() + ".insertOrder Start!");

        orderDto.setOrderId(String.valueOf(UUID.randomUUID()));
        orderDto.setOrderDate(String.valueOf(new Date()));

        String colNm = "OrderService";
        int res = orderMapper.insertOrder(orderDto, colNm);

        log.info(this.getClass().getName() + "insertOrder End!");
        return res;
    }

    @Override
    public List<OrderDto> findAllOrder(OrderDto orderDto) throws Exception {

        List<OrderDto> orderDtoList = orderMapper.findAllOrder(orderDto);

        return orderDtoList;
    }

    @Override
    public OrderDto findOneOrder(OrderDto orderDto) throws Exception {

        OrderDto rOrderDto = orderMapper.findOneOrder(orderDto);

        return rOrderDto;
    }
}
