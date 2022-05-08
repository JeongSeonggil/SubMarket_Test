package com.submarket.userservice.mapper;

import com.submarket.userservice.dto.SubDto;
import com.submarket.userservice.jpa.SubRepository;
import com.submarket.userservice.jpa.entity.SubEntity;
import com.submarket.userservice.vo.ResponseSub;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubMapper {
    SubMapper INSTANCE = Mappers.getMapper(SubMapper.class);

    SubEntity subDtoToSubEntity(SubDto subDto);

    SubDto subEntityToSubDto(SubEntity subEntity);

    ResponseSub responseSubToSubDto(SubDto subDto);
}
