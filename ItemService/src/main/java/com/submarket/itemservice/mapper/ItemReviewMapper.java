package com.submarket.itemservice.mapper;

import com.submarket.itemservice.dto.ItemReviewDto;
import com.submarket.itemservice.jpa.entity.ItemEntity;
import com.submarket.itemservice.jpa.entity.ItemReviewEntity;
import org.mapstruct.factory.Mappers;

public interface ItemReviewMapper {
    ItemReviewMapper INSTANCE = Mappers.getMapper(ItemReviewMapper.class);

    ItemReviewDto itemReviewEntityToItemDto(ItemReviewEntity itemReviewEntity);

    ItemReviewEntity itemReviewDtoToItemEntity(ItemReviewDto itemReviewDto);
}
