package com.sprta.deliveryproject.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprta.deliveryproject.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//review Request 땐 contents 만 사용
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewResponseDto {

    private final Long reviewId;
    private final String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime modifiedAt;
    //review 의 기반이 되는 order 에 대한 여러 정보를 orderId를 통해 가져오고, review 가 가진 member 에서 리뷰작성자의 닉네임을 가져온다.
    private final String profileName;
    private final Long orderId;


    public ReviewResponseDto(Review review) {
        this.reviewId = review.getId();
        this.contents = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
        this.orderId = review.getOrder().getId();
        this.profileName = review.getMember().getProfilename();
    }

}
