package com.sprta.deliveryproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity     //Entity클래스
@Getter
@Table(name = "shop")  //DB제작시 추가
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name="shopname",nullable = false)
    String shopname;               //가게이름

    @Column(name = "review_count")
    Integer review_count;           //리뷰 개수

    @Column(name = "adress")
    String adress;                  //가게주소

    @Column(name="phone_number")
    String phone_number;            //전화번호


    @JsonIgnore
    @JoinColumn(name="category_id")
    @ManyToOne
    Category category;              //카테고리

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.REMOVE})
    private List<Review> reviewList = new ArrayList<>();           //리뷰

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.REMOVE})
    private List<ShopLike> likeList = new ArrayList<>();            //좋아요
}
