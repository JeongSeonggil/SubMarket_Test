package com.submarket.itemservice.service.impl;

import com.submarket.itemservice.jpa.ItemReviewRepository;
import com.submarket.itemservice.service.IItemReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "ItemReviewService")
@Slf4j
@RequiredArgsConstructor
public class ItemReviewService implements IItemReviewService {
    private final ItemReviewRepository itemReviewRepository;
}
