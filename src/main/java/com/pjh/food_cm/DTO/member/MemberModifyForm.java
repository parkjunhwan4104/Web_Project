package com.pjh.food_cm.DTO.member;

import com.pjh.food_cm.domain.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberModifyForm {

    private Long id;

    private String loginId;

    @NotBlank
    private String loginPw;

    @NotBlank
    private String name;
    @NotBlank
    private String nickname;

    @NotBlank
    private String email;


    public MemberModifyForm(Member member){
        this.id=member.getId();
        this.loginId=member.getLoginId();
        this.loginPw=member.getLoginPw();
        this.name=member.getName();
        this.nickname=member.getNickname();
        this.email=member.getEmail();
    }

}



















