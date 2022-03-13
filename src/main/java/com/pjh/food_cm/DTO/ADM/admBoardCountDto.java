package com.pjh.food_cm.DTO.ADM;


import com.pjh.food_cm.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class admBoardCountDto {

    private String name;
    private int articleCount;

    public admBoardCountDto(Board board){
        this.name=board.getName();
        this.articleCount=board.getArticles().size();
    }



}
