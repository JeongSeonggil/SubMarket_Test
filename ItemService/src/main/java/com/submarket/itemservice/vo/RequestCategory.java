package com.submarket.itemservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCategory {
    private int categorySeq;
    private String categoryName;
}
