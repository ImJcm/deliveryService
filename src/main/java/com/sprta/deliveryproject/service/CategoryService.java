package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.CategoryResponseDto;
import com.sprta.deliveryproject.dto.ShopResponseDto;
import com.sprta.deliveryproject.entity.Category;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public ResponseEntity<ApiResponseDto> getCategories() {
        List<Category> categoryList = categoryRepository.findAllByOrderByIdAsc();

        List<CategoryResponseDto> newCategoryList = categoryList.stream().map(CategoryResponseDto::new).toList();

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "전체 카테고리 조회 성공", newCategoryList));
    }
}
