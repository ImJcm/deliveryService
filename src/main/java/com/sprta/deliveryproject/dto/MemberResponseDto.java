package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.MemberRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {
    private Long id;
    private String username;
    private String profilename;
    private String email;

    public MemberResponseDto(Member origin_member) {
        this.id = origin_member.getId();
        this.username = origin_member.getUsername();
        this.email = origin_member.getEmail();
        this.profilename = origin_member.getProfilename();
    }
}
