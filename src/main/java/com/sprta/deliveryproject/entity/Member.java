package com.sprta.deliveryproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sprta.deliveryproject.dto.ProfileRequestDto;
import com.sprta.deliveryproject.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity     //Entity클래스
@Getter
@NoArgsConstructor
@Table(name = "member")  //DB제작시 추가
//@EqualsAndHashCode
public class Member extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id",nullable = false,updatable = false,unique = true)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;                    //아이디

    @Column(name = "password", nullable = false)
    private String password;                    //비밀번호

    @Column(name = "profilename", nullable = false)
    private String profilename;                         //닉네임

    @Column(name = "email", nullable = false)
    private String email;                       //이메일

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;        //어드민인지 권한

    @OneToMany(mappedBy="member", cascade = {CascadeType.REMOVE})     //가게 좋아요 목록
    List<ShopLike> shopLikeList = new ArrayList<>();

    @OneToMany(mappedBy="member", cascade = {CascadeType.REMOVE})        //주문에 대한 리뷰 목록
    List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE})       //사용자가 주문한 목록
    List<Order> orderList = new ArrayList<>();

    public Member(String username, String password, String profilename, String email, MemberRoleEnum role) {
        this.username = username;
        this.password = password;
        this.profilename = profilename;
        this.email = email;
        this.role = role;
    }

    public void modify(ProfileRequestDto profileRequestDto) {
        this.username = profileRequestDto.getUsername();
        this.password = profileRequestDto.getPassword();
        this.profilename = profileRequestDto.getProfileName();
        this.email = profileRequestDto.getEmail();
    }
}
