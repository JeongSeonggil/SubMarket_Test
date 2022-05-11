package com.submarket.itemservice.mapper;

import com.submarket.itemservice.dto.GroupDto;
import com.submarket.itemservice.jpa.entity.GroupEntity;
import org.mapstruct.factory.Mappers;

public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDto groupEntityToGroupDto(GroupEntity groupEntity);

    GroupEntity groupDtoToGroupEntity(GroupDto groupDto);

}
