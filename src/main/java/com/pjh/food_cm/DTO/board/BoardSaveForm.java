package com.pjh.food_cm.DTO.board;



import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardSaveForm {


    private String name;


    private String detail;
}
