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
    public OrderDto findOneOrder(int OrderSeq) throws Exception {
        return null;
    }

    @Override
    public List<OrderDto> findAllOrder(int userSeq) throws Exception {
        log.info(this.getClass().getName() + "findAllOrder Start!");
        List<OrderDto> orderDtoList = new LinkedList<>();

        MongoCollection<Document> col = mongodb.getCollection("OrderService");

        Document projection = new Document();
        projection.append("orderSeq", "$orderSeq");
        projection.append("userSeq", "$userSeq");
        projection.append("itemSeq", "$itemSeq");
        projection.append("sellerSeq", "$sellerSeq");
        projection.append("orderDate", "$orderDate");
        projection.append("_id", "0");

        Document query = new Document();
        query.append("userSeq", userSeq);


        FindIterable<Document> rs = col.find(query).projection(projection);

        rs.forEach(document -> {

            if (document == null) {
                document = new Document();
            }

            OrderDto orderDto = new OrderDto();
            orderDto.setOrderSeq(document.getInteger("orderSeq"));
            orderDto.setItemSeq(document.getInteger("itemSeq"));
            orderDto.setUserSeq(document.getInteger("userSeq"));
            orderDto.setSellerSeq(document.getInteger("sellerSeq"));
            orderDto.setOrderDate(CmmUtil.nvl(document.getString("orderDate")));

            orderDtoList.add(orderDto);
        });

        log.info(this.getClass().getName() + "findAllOrder End!");
        return orderDtoList;
    }
}
