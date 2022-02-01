package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.article.ArticleDTO;
import com.pjh.food_cm.DTO.article.ArticleModifyForm;
import com.pjh.food_cm.DTO.article.ArticleSaveForm;
import com.pjh.food_cm.DTO.member.MemberModifyForm;
import com.pjh.food_cm.dao.ArticleRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Board;
import com.pjh.food_cm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Member member, Board board){
        Article article= Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );
        article.setMember(member);
        article.setBoard(board);
        articleRepository.save(article);
    }

    public  Optional<Article> findById(Long id){   //게시물을 찾고
        return articleRepository.findById(id);
    }

    public Article getById(Long id) throws NoSuchElementException{     //바로 위에 findyById를 이용하여 articleOptional에 찾은 아티클을 담는데 찾은 아티클이 없다면 에러메세지를 출력 있다면 해당 아티클 리턴
        Optional<Article> articleOptional = findById(id);

        articleOptional.orElseThrow(
                ()-> new NoSuchElementException("해당 게시물은 존재하지 않습니다.")
        );
        return articleOptional.get();

    }

    public ArticleDTO getArticle(Long id){
        Article findArticle = getById(id);
        ArticleDTO articleDTO=new ArticleDTO(findArticle);
        return articleDTO;
    }

    @Transactional
    public void modifyArticle(ArticleModifyForm articleModifyForm,Long id){
        Article findArticle=getById(id);

        findArticle.modifyArticle(
                articleModifyForm.getTitle(),
                articleModifyForm.getBody()
        );
    }

    public List<ArticleDTO> getArticleList(){
        List<Article> articleList = articleRepository.findAll();
        List<ArticleDTO> articleDTOList=new ArrayList<>();

        for(Article article:articleList){
            ArticleDTO articleDTO=new ArticleDTO(article);
            articleDTOList.add(articleDTO);
        }
        return articleDTOList;
    }

    @Transactional
    public void delete(Long id){
        Article findArticle=getById(id);

        articleRepository.delete(findArticle);
    }


}

















