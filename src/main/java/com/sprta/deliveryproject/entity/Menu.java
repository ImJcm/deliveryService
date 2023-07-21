package com.sprta.deliveryproject.entity;

import com.sprta.deliveryproject.dto.MenuRequestDto;
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
    @Column(name="introduce")
    String introduce;

    public Menu(Shop shop,MenuRequestDto menuRequestDto){
        this.shop = shop;
        this.menuname = menuRequestDto.getMenuname();
        this.price = menuRequestDto.getPrice();
        this.introduce = menuRequestDto.getIntroduce();

    }
    public void update(Menu menu){
        this.shop = menu.getShop();
        this.menuname = menu.getMenuname();
        this.price = menu.getPrice();
        this.introduce = menu.getIntroduce();
    }
    public Menu() {

    }
}
