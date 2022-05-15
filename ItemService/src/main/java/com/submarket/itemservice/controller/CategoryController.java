package com.submarket.itemservice.controller;

import com.submarket.itemservice.dto.CategoryDto;
import com.submarket.itemservice.jpa.entity.CategoryEntity;
import com.submarket.itemservice.service.impl.CategoryService;
import com.submarket.itemservice.vo.RequestCategory;
import com.submarket.itemservice.vo.ResponseCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<Object> findAllCategory() throws Exception {
        return null;
    }


    @GetMapping("/category/{categorySeq}") // 그룹에 들어있는 상품 조회
    public ResponseEntity<CategoryDto> getCategoryInfo(@PathVariable int categorySeq) throws Exception {
        log.info(this.getClass().getName() + "getCategoryInfo Start!");

        CategoryDto pCategoryDto = new CategoryDto();
        pCategoryDto.setCategorySeq(categorySeq);

        CategoryDto categoryDto = categoryService.findItemInfoByCategory(pCategoryDto);


        if (categoryDto == null) {
            log.info("categoryDto is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        log.info(this.getClass().getName() + ".getCategoryInfo End!");
        return ResponseEntity.ok().body(categoryDto);
    }


}
