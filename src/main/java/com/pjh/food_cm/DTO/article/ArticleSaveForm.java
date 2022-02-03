package com.pjh.food_cm.DTO.article;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class ArticleSaveForm {


    private String title;

    private String body;

    private Long board_id;

}
