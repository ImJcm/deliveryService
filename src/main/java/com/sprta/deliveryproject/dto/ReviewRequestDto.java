package com.sprta.deliveryproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewRequestDto {
    private Long orderId;
    private Long reviewId;
    private String contents;

}
