package com.submarket.orderservice.service;

import com.submarket.orderservice.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    int insertOrder(OrderDto orderDto) throws Exception;

    List<OrderDto>  findAllOrder(OrderDto orderDto) throws Exception;

    OrderDto findOneOrder(OrderDto orderDto) throws Exception;

}
