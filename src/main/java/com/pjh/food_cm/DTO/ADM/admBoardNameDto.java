package com.pjh.food_cm.DTO.ADM;

import com.pjh.food_cm.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class admBoardNameDto {

    private String name;
    private LocalDateTime regDate;

    public admBoardNameDto(Board board){
        this.name=board.getName();
        this.regDate=board.getRegDate();
    }
}
