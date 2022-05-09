package com.submarket.orderservice.controller;

import com.submarket.orderservice.dto.OrderDto;
import com.submarket.orderservice.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/{orderId}") // 주문 정보 가져오기
    public ResponseEntity<OrderDto> findOneOrder(@PathVariable("orderId") String orderId) throws Exception {
        log.info(this.getClass().getName() + "findOneOrder Start!");
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(String.valueOf(orderId));

        OrderDto rOrderDto = orderService.findOneOrder(orderDto);
        log.info(this.getClass().getName() + "findOneOrder End!");


        return ResponseEntity.status(HttpStatus.FOUND).body(rOrderDto);
    }


    @GetMapping("/order/user/{userSeq}") // 주문 내역 확인 (token 에서 사용자 Seq 가져오기)
    public ResponseEntity<List<OrderDto>> findAllOrder(@PathVariable("userSeq") String userSeq) throws Exception {
        log.info(this.getClass().getName() + "findAllOrder Start!");
        OrderDto orderDto = new OrderDto();
        orderDto.setUserSeq(Integer.parseInt(userSeq));

        List<OrderDto> orderDtoList = orderService.findAllOrder(orderDto);

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

        orderService.insertOrder(orderDto);

        log.info(this.getClass().getName() + ".insertOrder End!");
        return ResponseEntity.status(HttpStatus.CREATED).body("주문 생성 완료");
    }
}
