package com.submarket.orderservice.service;

import com.submarket.orderservice.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    int insertOrder(OrderDto orderDto) throws Exception;

    List<OrderDto>  findAllOrder(int orderSeq) throws Exception;

    OrderDto findOneOrder(int userSeq) throws Exception;

}
