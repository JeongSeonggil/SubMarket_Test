package com.submarket.itemservice.mapper;

import com.submarket.itemservice.dto.CategoryDto;
import com.submarket.itemservice.jpa.entity.CategoryEntity;
import org.mapstruct.factory.Mappers;

public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryEntityToCategoryDto(CategoryEntity categoryEntity);

    CategoryDto categoryDtoToCategoryEntity(CategoryDto categoryDto);
}
