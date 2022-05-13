package com.submarket.itemservice.service.impl;

import com.submarket.itemservice.jpa.CategoryRepository;
import com.submarket.itemservice.jpa.ItemRepository;
import com.submarket.itemservice.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("CategoryService")
@Slf4j
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    // TODO: 2022/05/13 목록 조회
}
