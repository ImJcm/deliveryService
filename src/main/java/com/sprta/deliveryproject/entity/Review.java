package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id",nullable = false,updatable = false,unique = true)
    private Long id;

    @Column(name = "review_content")
    private String content;      //리뷰 내용

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

    public void updateContent(String content){
        this.content=content;
    }
    public String toString() {
        Long id = this.getId();
        return "Review(id=" + id + ", content=" + this.getContent() + ", createdAt=" + this.getCreatedAt() + ", modifiedAt=" + this.getModifiedAt() + ", shopId=" + this.getShop().getId() + ", memberId=" + this.getMember().getId() + ")";
    }

}
