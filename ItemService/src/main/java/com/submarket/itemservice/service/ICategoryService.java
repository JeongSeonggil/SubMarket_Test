package com.submarket.itemservice.service;

import com.submarket.itemservice.dto.CategoryDto;
import com.submarket.itemservice.jpa.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    CategoryDto findItemInfoByCategory(CategoryDto categoryDto) throws Exception;

    CategoryDto findCategory(CategoryDto categoryDto) throws Exception;

    List<CategoryDto> findAllCategory() throws Exception;

}
