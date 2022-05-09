package com.submarket.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderSeq;
    private int itemSeq;
    private int userSeq;
    private int sellerSeq;

    private String orderDate;
}
