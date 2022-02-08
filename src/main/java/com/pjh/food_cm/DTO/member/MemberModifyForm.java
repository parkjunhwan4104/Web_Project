package com.pjh.food_cm.DTO.member;

import com.pjh.food_cm.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MemberModifyForm {

    private Long id;

    private String loginId;

    @NotBlank(message="비밀번호를 입력해 주세요")
    private String loginPw;


    private String name;

    @NotBlank(message="닉네임을 입력해 주세요")
    private String nickname;

    @NotBlank(message="이메일을 입력해 주세요")
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



















