package com.submarket.itemservice.service;

import com.submarket.itemservice.dto.CategoryDto;
import com.submarket.itemservice.jpa.entity.CategoryEntity;

public interface ICategoryService {
    CategoryEntity getCategoryInfo(CategoryDto categoryDto) throws Exception;

}
