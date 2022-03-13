package com.pjh.food_cm.DTO.ADM;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardStateDTO {
    private Long totalBoardCount;
    private List<admBoardCountDto> boardCountDtoList;
    private List<admBoardNameDto> boardNameDtoList;
}
