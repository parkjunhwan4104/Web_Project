package com.pjh.food_cm.DTO.article;


import com.pjh.food_cm.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDTO {
    
    private Long id;
    
    private String title;
    
    private String body;
    
    private String authorName; //작성자 이름

    private String boardName;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public ArticleDTO(Article article){
        this.id=article.getId();
        this.title=article.getTitle();
        this.body=article.getBody();
        this.authorName=article.getMember().getNickname();
        this.boardName=article.getBoard().getName();

        this.regDate=article.getRegDate();
        this.updateDate=article.getUpdateDate();
    }
}
