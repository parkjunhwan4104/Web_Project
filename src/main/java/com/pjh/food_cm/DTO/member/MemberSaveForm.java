package com.pjh.food_cm.DTO.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data  //다른클래스에 가서도 쓸수있도록함
public class MemberSaveForm {  //멤버클래스에서 만든 멤버 entity를 누구나 다 쉽게 건드려서 값을 바꾸는 일을 할 수 없도록 잠궈놓는거



    //회원가입시 필요한 정보를 저장
    @NotBlank
    private String loginId;

    @NotBlank
    private String loginPw;

    @NotBlank
    private String name;

    @NotBlank
    private String nickname;

    @NotBlank
    private String email;


}
