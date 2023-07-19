package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Entity     //Entity클래스
@Getter
@Setter
@Table(name = "member")  //DB제작시 추가
//@EqualsAndHashCode
public class Member extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;                    //아이디

    @Column(name = "password", nullable = false)
    private String password;                    //비밀번호

    @Column(name = "profilename", nullable = false)
    private String profilename;                         //닉네임

    @Column(name="role")
    private MemberRoleEnum role;        //어드민인지 권한

}
