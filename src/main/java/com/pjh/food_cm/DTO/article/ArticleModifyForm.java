package com.pjh.food_cm.DTO.article;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor

public class ArticleModifyForm {

    @NotBlank(message="제목을 입력해 주세요")
    private String title;

    @NotBlank(message="내용을 입력해 주세요")
    private String body;

    private Long board_id;

    private String boardName;


}
