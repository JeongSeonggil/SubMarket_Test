package com.submarket.itemservice.service.impl;

import com.submarket.itemservice.dto.ItemDto;
import com.submarket.itemservice.dto.ItemReviewDto;
import com.submarket.itemservice.jpa.ItemReviewRepository;
import com.submarket.itemservice.jpa.entity.ItemEntity;
import com.submarket.itemservice.jpa.entity.ItemReviewEntity;
import com.submarket.itemservice.mapper.ItemMapper;
import com.submarket.itemservice.mapper.ItemReviewMapper;
import com.submarket.itemservice.service.IItemReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "ItemReviewService")
@Slf4j
@RequiredArgsConstructor
public class ItemReviewService implements IItemReviewService {
    private final ItemReviewRepository itemReviewRepository;
    private final ItemService itemService;

    @Override
    public int saveReview(ItemReviewDto itemReviewDto, int itemSeq) throws Exception {
        log.info(this.getClass().getName() + ".saveReview Start!");
        ItemDto itemDto = new ItemDto();
        itemDto.setItemSeq(itemSeq);
        // TODO: 2022-05-16 사용자는 하나의 상품에 하나의 리뷰만 가능하기 때문에 확인 로직, 사용자가 상품을 구독중인지 확인 로직

        ItemDto items = itemService.findItemInfo(itemDto);
        ItemEntity itemEntity = ItemMapper.INSTANCE.itemDtoToItemEntity(items);
        itemReviewDto.setItem(itemEntity);
        // 리뷰 생성 로직

        ItemReviewEntity itemReviewEntity;
        itemReviewEntity = ItemReviewMapper.INSTANCE.itemReviewDtoToItemEntity(itemReviewDto);

        itemReviewRepository.save(itemReviewEntity);


        log.info(this.getClass().getName() + ".saveReview End!");
        return 1;
    }

    @Override
    public int modifyReview(ItemReviewDto itemReviewDto) throws Exception {
        log.info(this.getClass().getName() + ".modifyReview Start!");

        int reviewSeq = itemReviewDto.getReviewSeq();
        String reviewContents = itemReviewDto.getReviewContents();
        String reviewDate = itemReviewDto.getReviewDate();
        int reviewStar = itemReviewDto.getReviewStar();


        itemReviewRepository.modifyItemReview(reviewContents, reviewDate, reviewStar, reviewSeq);

        log.info(this.getClass().getName() + ".modifyReview End!");
        return 1;
    }
}