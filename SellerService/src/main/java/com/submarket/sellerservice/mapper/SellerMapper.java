package com.submarket.sellerservice.mapper;

import com.submarket.sellerservice.dto.SellerDTO;
import com.submarket.sellerservice.jpa.entity.SellerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper {
    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    @Mapping(source = "sellerEncPassword", target = "sellerPassword")
    SellerEntity sellerDTOToSellerEntity(SellerDTO sellerDTO);

    SellerDTO sellerEntityToSellerDTO(SellerEntity sellEntity);
}
