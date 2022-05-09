package com.submarket.orderservice.controller;

import com.submarket.orderservice.dto.OrderDto;
import com.submarket.orderservice.service.impl.OrderService;
import com.submarket.orderservice.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/{orderSeq}") // 주문 정보 가져오가
    public ResponseEntity<OrderDto> findOneOrder(@PathVariable() int orderSeq) throws Exception {
        log.info(this.getClass().getName() + "findOneOrder Start!");


        log.info(this.getClass().getName() + "findOneOrder End!");


        return null;
    }


    @GetMapping("/order/user/{user-seq}") // 주문 내역 확인 (token 에서 사용자 Seq 가져오기)
    public ResponseEntity<List<OrderDto>> findAllOrder(@PathVariable("user-seq") String userSeq) throws Exception {
        log.info(this.getClass().getName() + "findAllOrder Start!");

        List<OrderDto> orderDtoList = orderService.findAllOrder(Integer.parseInt(userSeq));

        if (orderDtoList.isEmpty()) {
            log.info("주문 없음");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        log.info(this.getClass().getName() + "findAllOrder End!");

        return ResponseEntity.status(HttpStatus.OK).body(orderDtoList);
    }


    @PostMapping("/order")
    public ResponseEntity<String> insertOrder(@RequestBody OrderDto orderDto) throws Exception {
        log.info(this.getClass().getName() + ".insertOrder Start!");
        // OrderSeq : 주문 식별자 (수정)
        orderDto.setOrderDate(String.valueOf(new Date()));

        orderService.insertOrder(orderDto);

        log.info(this.getClass().getName() + ".insertOrder End!");
        return null;
    }
}
