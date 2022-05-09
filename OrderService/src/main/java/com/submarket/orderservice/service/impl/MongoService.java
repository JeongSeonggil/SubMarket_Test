package com.submarket.orderservice.service.impl;

import com.submarket.orderservice.dto.MongoDto;
import com.submarket.orderservice.mapper.IMongoMapper;
import com.submarket.orderservice.service.IMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Slf4j
@Service(value = "MongoService")
public class MongoService implements IMongoService {
    @Resource(name = "MongoMapper")
    private IMongoMapper mongoMapper;

    @Override
    public void mongoTest() throws Exception {
        log.info(this.getClass().getName() + ".mongoTest Start!");


        String conNm = "Mongo_Test";

        MongoDto pDTO = new MongoDto();
        pDTO.setUser_nm("정성길");
        pDTO.setAddr("서울");
        pDTO.setEmail("dataofsg02@gmail.com");

        mongoMapper.insertData(pDTO, conNm);


        log.info(this.getClass().getName() + ".mongoTest End!");


    }
}
