package com.pjh.food_cm.DTO.board;



import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardSaveForm {

    @NotBlank
    private String name;

    @NotBlank
    private String detail;
}
