package com.pjh.food_cm.DTO.article;

import com.pjh.food_cm.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleListDTO {   //게시판에서 게시물이 여러개가 나올텐데 거기서 리스트를 구성하는 필드들이 있음(자세한 정보는 articleDto에 있음)

    private Long id; //아티클 번호

    private String title;

    private String nickname;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public ArticleListDTO(Article article){
        this.id= article.getId();
        this.title=article.getTitle();
        this.nickname=article.getMember().getNickname();
        this.regDate=article.getRegDate();
        this.updateDate=article.getUpdateDate();
    }




















}
