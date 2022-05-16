package com.submarket.itemservice.service;

import com.submarket.itemservice.dto.ItemReviewDto;

public interface IItemReviewService {
    int saveReview(ItemReviewDto itemReviewDto, int itemSeq) throws Exception;

    int modifyReview(ItemReviewDto itemReviewDto) throws Exception;

    int deleteReview(ItemReviewDto itemReviewDto) throws Exception;
}