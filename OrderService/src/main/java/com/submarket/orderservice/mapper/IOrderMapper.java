package com.submarket.orderservice.mapper;

import com.submarket.orderservice.dto.OrderDto;

import java.util.List;

public interface IOrderMapper {

    int insertOrder(OrderDto orderDto, String colNm) throws Exception;

    OrderDto findOneOrder(int OrderSeq) throws Exception;

    List<OrderDto> findAllOrder(int userSeq) throws Exception;

}
