package com.sprta.deliveryproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity     //Entity클래스
@Getter
@Table(name = "shop_like")  //DB제작시 추가
@NoArgsConstructor
public class ShopLike extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="member_id")
    @JsonIgnore
    @ManyToOne
    private Member member;

    @JoinColumn(name="shop_id")
    @JsonIgnore
    @ManyToOne
    private Shop shop;

}
