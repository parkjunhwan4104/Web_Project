package com.pjh.food_cm.DTO.member;

import com.pjh.food_cm.DTO.article.ArticleDTO;
import com.pjh.food_cm.domain.Board;
import com.pjh.food_cm.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class myPageDTO {

    private String loginId;
    private String nickname;

    private List<ArticleDTO> articles;

    public myPageDTO(Member member, List<ArticleDTO> articles){

        this.loginId = member.getLoginId();
        this.nickname = member.getNickname();

        this.articles = articles;

    }
}
