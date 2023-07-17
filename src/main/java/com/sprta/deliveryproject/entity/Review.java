package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id",nullable = false,updatable = false,unique = true)
    private Long id;

    @Column(name = "review_content")
    private String content;      //리뷰 내용

    @CreatedDate
    @Column(name="created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @JoinColumn(name = "shop_id")
    @ManyToOne
    private Shop shop;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @Builder
    public Review(String content, Shop shop, Member member) {
        this.content = content;
        this.shop = shop;
        this.member = member;
    }
    public String toString() {
        Long id = this.getId();
        return "Review(id=" + id + ", content=" + this.getContent() + ", createdAt=" + this.getCreatedAt() + ", shopId=" + this.getShop().getId() + ", memberId=" + this.getMember().getId() + ")";
    }

}
