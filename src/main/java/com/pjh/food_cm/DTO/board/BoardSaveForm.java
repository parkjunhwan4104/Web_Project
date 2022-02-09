package com.pjh.food_cm.DTO.board;



import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardSaveForm {

    @NotBlank(message ="게시판 이름을 입력해 주세요")
    private String name;

    @NotBlank(message ="게시판 설명을 입력해 주세요")
    private String detail;
}
