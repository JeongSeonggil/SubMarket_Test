package com.submarket.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private int itemSeq;
    private int sellerSeq;
    private String itemTitle;
    private String itemContents;

    private int itemPrice;
    private int itemCount; // 상품 수

    private int itemStatus; // 활성화

    // TODO: 2022/05/11 FK 설정
}