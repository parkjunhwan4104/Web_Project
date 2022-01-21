package com.pjh.food_cm.DTO.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberModifyForm {

    @NotBlank
    private String loginPw;

    @NotBlank
    private String nickname;

    @NotBlank
    private String email;

}
