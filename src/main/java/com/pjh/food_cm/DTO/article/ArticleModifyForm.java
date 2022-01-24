package com.pjh.food_cm.DTO.article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleModifyForm {

    private String title;
    private String body;
}
