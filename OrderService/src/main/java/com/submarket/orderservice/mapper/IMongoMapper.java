package com.submarket.orderservice.mapper;

import com.submarket.orderservice.dto.MongoDto;

public interface IMongoMapper {
    int insertData(MongoDto pDTO, String colNm) throws Exception;
}
