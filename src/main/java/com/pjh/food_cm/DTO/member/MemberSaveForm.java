package com.pjh.food_cm.DTO.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data  //다른클래스에 가서도 쓸수있도록함
public class MemberSaveForm {  //멤버클래스에서 만든 멤버 entity를 누구나 다 쉽게 건드려서 값을 바꾸는 일을 할 수 없도록 잠궈놓는거



    //회원가입시 필요한 정보를 저장
    @NotBlank(message="아이디를 입력해 주세요") // 공백으로 입력시 이러한 메세지가 뜨도록함
    private String loginId;

    @NotBlank(message="비밀번호를 입력해 주세요")
    private String loginPw;

    @NotBlank(message="이름을 입력해 주세요")
    private String name;

    @NotBlank(message="닉네임을 입력해 주세요")
    private String nickname;

    @NotBlank(message="이메일을 입력해 주세요")
    private String email;


}
























