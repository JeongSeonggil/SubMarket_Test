package com.submarket.orderservice.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.submarket.orderservice.config.AbstractMongoDBComon;
import com.submarket.orderservice.dto.OrderDto;
import com.submarket.orderservice.mapper.IOrderMapper;
import com.submarket.orderservice.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component(value = "OrderMapper")
public class OrderMapper extends AbstractMongoDBComon implements IOrderMapper {

    @Override
    public int insertOrder(OrderDto orderDto, String colNm) throws Exception {
        log.info(this.getClass().getName() + ".insertOrder Start!");

        int res = 0;

        super.createCollection(colNm);

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        col.insertOne(new Document(new ObjectMapper().convertValue(orderDto, Map.class)));

        res = 1;

        log.info(this.getClass().getName() + "insertOrder End!");

        return res;
    }

    @Override
    public OrderDto findOneOrder(OrderDto orderDto) throws Exception {
        log.info(this.getClass().getName() + "findOneOrder Start!");

        String orderId = orderDto.getOrderId();

        MongoCollection<Document> col = mongodb.getCollection("OrderService");

        Document query = new Document();
        query.append("orderId", orderId);

        Document projection = new Document();
        projection.append("orderId", "$orderId");
        projection.append("userSeq", "$userSeq");
        projection.append("itemSeq", "$itemSeq");
        projection.append("sellerSeq", "$sellerSeq");
        projection.append("orderDate", "$orderDate");

        FindIterable<Document> findIterable = col.find(query).projection(projection);

        OrderDto rOrderDto = new OrderDto();
        findIterable.forEach(document -> {
            rOrderDto.setOrderId(document.getString("orderId"));
            rOrderDto.setItemSeq(document.getInteger("itemSeq"));
            rOrderDto.setUserSeq(document.getInteger("userSeq"));
            rOrderDto.setSellerSeq(document.getInteger("sellerSeq"));
            rOrderDto.setOrderDate(CmmUtil.nvl(document.getString("orderDate")));
        });

        log.info(this.getClass().getName() + "findOneOrder End!");
        return rOrderDto;
    }

    @Override
    public List<OrderDto> findAllOrder(OrderDto orderDto) throws Exception {

        int userSeq = orderDto.getUserSeq();

        log.info(this.getClass().getName() + "findAllOrder Start!");
        List<OrderDto> orderDtoList = new LinkedList<>();

        MongoCollection<Document> col = mongodb.getCollection("OrderService");

        Document projection = new Document();
        projection.append("orderId", "$orderId");
        projection.append("userSeq", "$userSeq");
        projection.append("itemSeq", "$itemSeq");
        projection.append("sellerSeq", "$sellerSeq");
        projection.append("orderDate", "$orderDate");
//        projection.append("_id", "0");

        Document query = new Document();
        query.append("userSeq", userSeq);


        FindIterable<Document> rs = col.find(query).projection(projection);

        rs.forEach(document -> {

            if (document == null) {
                document = new Document();
            }

            OrderDto forOrderDto = new OrderDto();
            forOrderDto.setOrderId(document.getString("orderId"));
            forOrderDto.setItemSeq(document.getInteger("itemSeq"));
            forOrderDto.setUserSeq(document.getInteger("userSeq"));
            forOrderDto.setSellerSeq(document.getInteger("sellerSeq"));
            forOrderDto.setOrderDate(CmmUtil.nvl(document.getString("orderDate")));

            orderDtoList.add(forOrderDto);
        });

        log.info(this.getClass().getName() + "findAllOrder End!");
        return orderDtoList;
    }
}
