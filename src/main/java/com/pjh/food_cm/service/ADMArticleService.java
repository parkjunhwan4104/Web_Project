package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.article.ArticleListDTO;
import com.pjh.food_cm.dao.ArticleRepository;
import com.pjh.food_cm.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ADMArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleListDTO> getArticleList(){
        List<ArticleListDTO> articleListDTOList=new ArrayList<>();

        List<Article> articleList = articleRepository.findAll();

        for(Article article:articleList){
            ArticleListDTO articleListDTO=new ArticleListDTO(article);
            articleListDTOList.add(articleListDTO);
        }
        return articleListDTOList;
    }

    @Transactional
    public void deleteArticle(Long id){
        Optional<Article> articleOptional = articleRepository.findById(id);

        articleOptional.orElseThrow(
                ()->new IllegalStateException("존재하지 않는 게시글 입니다.")
        );

        articleRepository.delete(articleOptional.get());
    }

}






































