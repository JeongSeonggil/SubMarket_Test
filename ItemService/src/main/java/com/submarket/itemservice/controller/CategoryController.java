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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category") // 그룹 정보 조회
    public ResponseEntity<ResponseCategory> getCategoryInfo(@RequestBody RequestCategory requestCategory) throws Exception {
        log.info(this.getClass().getName() + "getCategoryInfo Start!");

        log.info("Request Seq : " + requestCategory.getCategorySeq());
        log.info("Request Name : " + requestCategory.getCategoryName());

        CategoryDto pCategoryDto = new CategoryDto();
        pCategoryDto.setCategorySeq(requestCategory.getCategorySeq());
        pCategoryDto.setCategoryName(requestCategory.getCategoryName());

        CategoryEntity categoryDto = categoryService.getCategoryInfo(pCategoryDto);


        if (categoryDto == null) {
            log.info("categoryDto is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        ResponseCategory responseCategory = new ResponseCategory();

        requestCategory.setCategorySeq(categoryDto.getCategorySeq());
        requestCategory.setCategoryName(categoryDto.getCategoryName());

        log.info(this.getClass().getName() + ".getCategoryInfo End!");
        return ResponseEntity.ok().body(responseCategory);
    }

}
