package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity     //Entity클래스
@Getter
@Table(name = "category")  //DB제작시 추가
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="category_name")
    String category_name;

    @Column(name="image_src")
    String image_src;
}
