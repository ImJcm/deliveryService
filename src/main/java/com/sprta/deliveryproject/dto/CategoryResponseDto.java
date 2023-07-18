package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private Long id;
    private String category_name;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.category_name = category.getCategory_name();
    }
}