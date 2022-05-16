package com.submarket.itemservice.controller;

import com.submarket.itemservice.dto.ItemDto;
import com.submarket.itemservice.dto.ItemReviewDto;
import com.submarket.itemservice.service.impl.ItemReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ItemReviewController {
    private final ItemReviewService itemReviewService;

    @PostMapping("/item/{itemSeq}/review")
    public ResponseEntity<String> saveReview(@RequestBody ItemReviewDto itemReviewDto, @PathVariable int itemSeq) throws Exception {
        log.info(this.getClass().getName() + ".saveReview Start!");
        // TODO: 2022-05-16 사용자가 이미 작성한 리뷰가 있는지 확인 with UserSeq

        if (itemReviewDto.equals(null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("리뷰 정보를 입력해주세요");
        }

        int res = itemReviewService.saveReview(itemReviewDto, itemSeq);


        log.info(this.getClass().getName() + ".saveReview End!");

        return ResponseEntity.status(HttpStatus.CREATED).body("리뷰 작성 완료");
    }

}