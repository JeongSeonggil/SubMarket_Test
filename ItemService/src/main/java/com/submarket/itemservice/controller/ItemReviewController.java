package com.submarket.itemservice.controller;

import com.submarket.itemservice.service.impl.ItemReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ItemReviewController {
    private final ItemReviewService itemReviewService;
}
