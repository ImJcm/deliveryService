package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity     //Entity클래스
@Getter
@Table(name = "shop_like")  //DB제작시 추가
@NoArgsConstructor
//@EqualsAndHashCode
public class ShopLike extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //숍 id

    @JoinColumn(name="member_id")
    @ManyToOne
    private Member meber;
    @JoinColumn(name="shop_id")
    @ManyToOne
    private Shop shop;

}
