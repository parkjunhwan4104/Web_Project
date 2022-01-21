package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.article.ArticleSaveForm;
import com.pjh.food_cm.DTO.member.MemberModifyForm;
import com.pjh.food_cm.dao.ArticleRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Member member){
        Article article= Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );
        article.setMember(member);
        articleRepository.save(article);
    }



}

















