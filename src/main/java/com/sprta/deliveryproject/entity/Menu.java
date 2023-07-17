package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;

@Entity
@Getter
@Table(name="menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    Shop shop;
    @Column(name="menuname")
    String menuname;
    @Column(name ="price")
    Integer price;
}
