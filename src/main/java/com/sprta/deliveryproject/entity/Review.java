package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity     //Entity클래스
@Getter
@Table(name = "review")  //DB제작시 추가
public class Review extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="member_id")
    @ManyToOne
    private Member meber_id;        //멤버

    @Column(name="review_content")
    private String review_content;      //리뷰 내용

    @JoinColumn(name="shop_id")
    @ManyToOne
    private Shop shop;


}
