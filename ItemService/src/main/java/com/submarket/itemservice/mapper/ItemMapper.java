package com.submarket.itemservice.mapper;

import com.submarket.itemservice.dto.ItemDto;
import com.submarket.itemservice.jpa.entity.ItemEntity;
import org.mapstruct.factory.Mappers;

public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemEntity itemDtoToItemEntity(ItemDto itemDto);

    ItemDto itemEntityToItemDto(ItemEntity itemEntity);
}
