package com.sprta.deliveryproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity     //Entity클래스
@Getter
@Table(name = "shop_like")  //DB제작시 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopLike{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_like_id", unique = true, updatable = false, nullable = false)
    private Long id;

    @JoinColumn(name="member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name="shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Builder
    public ShopLike(Member member,Shop shop){
        this.member=member;
        this.shop=shop;
    }
}
