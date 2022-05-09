package com.submarket.orderservice.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.submarket.orderservice.config.AbstractMongoDBComon;
import com.submarket.orderservice.dto.MongoDto;
import com.submarket.orderservice.mapper.IMongoMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "MongoMapper")
@Slf4j
public class MongoMapper extends AbstractMongoDBComon implements IMongoMapper {

    @Override
    public int insertData(MongoDto pDTO, String colNm) throws Exception {
        log.info(this.getClass().getName() + ".insertData String!");

        int res = 0;

        super.createCollection(colNm);

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info(this.getClass().getName() + ".insertData End!");


        return res;
    }
}
