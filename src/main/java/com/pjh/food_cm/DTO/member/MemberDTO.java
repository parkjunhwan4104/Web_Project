package com.pjh.food_cm.DTO.member;

import com.pjh.food_cm.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor //매개변수없는 생성자를 기본적으로 1개 만들어줌
public class MemberDTO {

    private Long memberId;

    private String loginId;

    private String name;

    private String nickname;

    private String email;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public MemberDTO(Member member){
        this.memberId=member.getId();
        this.loginId=member.getLoginId();
        this.name=member.getName();
        this.nickname=member.getNickname();
        this.email=member.getEmail();
        this.regDate=member.getRegDate();
        this.updateDate=member.getUpdateDate();
    }
}
