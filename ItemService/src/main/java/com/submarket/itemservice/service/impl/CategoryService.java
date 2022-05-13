package com.submarket.itemservice.service.impl;

import com.submarket.itemservice.dto.CategoryDto;
import com.submarket.itemservice.jpa.CategoryRepository;
import com.submarket.itemservice.jpa.ItemRepository;
import com.submarket.itemservice.jpa.entity.CategoryEntity;
import com.submarket.itemservice.mapper.CategoryMapper;
import com.submarket.itemservice.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CategoryService")
@Slf4j
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    // TODO: 2022/05/13 목록 조회

    @Override
    public CategoryEntity getCategoryInfo(CategoryDto categoryDto) throws Exception {
        log.info(this.getClass().getName() + ".getCategoryInfo Start!");

        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryDto.getCategorySeq());

        CategoryEntity categoryEntity = categoryEntityOptional.get();

        if (categoryEntity == null) {
            throw new RuntimeException("그룹 정보 없음");
        }

        CategoryDto rCategoryDto = CategoryMapper.INSTANCE.categoryEntityToCategoryDto(categoryEntity);
        log.info(this.getClass().getName() + ".getCategoryInfo End!");
        return categoryEntity;
    }
}
