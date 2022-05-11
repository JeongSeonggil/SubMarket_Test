package com.submarket.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private int categorySeq; // 1, 2
    private String categoryName; // 식품, 음료
}
