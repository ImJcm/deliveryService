package com.sprta.deliveryproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprta.deliveryproject.entity.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String username;    //유저이름
    private String password;    //비밀번호
    private String profilename; //프로필명
    private String email;       //이메일
    private MemberRoleEnum role;//역할
    private List<ShopLikeResponseDto> shoplikeList; //멤버가 좋아요한 가게

    public MemberResponseDto(Member origin_member) {
        this.id = origin_member.getId();
        this.username = origin_member.getUsername();
        this.password = origin_member.getPassword();
        this.email = origin_member.getEmail();
        this.profilename = origin_member.getProfilename();
        this.role = origin_member.getRole();
        this.shoplikeList = origin_member.getShopLikeList().stream().map(ShopLikeResponseDto::new).toList();
    }
}