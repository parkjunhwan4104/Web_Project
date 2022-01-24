package com.pjh.food_cm.DTO.board;

import com.pjh.food_cm.DTO.article.ArticleListDTO;
import com.pjh.food_cm.domain.Board;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardDTO {
    private Long id;

    private String name;
    private String detail;

    private List<ArticleListDTO> articleListDTO;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public BoardDTO(Board board,List<ArticleListDTO> articleListDTO){
        this.id=board.getId();
        this.name=board.getName();
        this.detail=board.getDetail();
        this.articleListDTO=articleListDTO;
        this.regDate=board.getRegDate();
        this.updateDate=board.getUpdateDate();
    }
}
