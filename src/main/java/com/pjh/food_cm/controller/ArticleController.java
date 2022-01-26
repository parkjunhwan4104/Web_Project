package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.article.ArticleDTO;
import com.pjh.food_cm.DTO.article.ArticleModifyForm;
import com.pjh.food_cm.DTO.article.ArticleSaveForm;
import com.pjh.food_cm.dao.ArticleRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.ArticleService;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    private final MemberService memberService;
    @GetMapping("/articles/write")
    public String showWrite(Model model){
        model.addAttribute("articleSaveForm",new ArticleSaveForm());

        return "user/article/write";
    }

    @PostMapping("/articles/write")
    public String doWrite(@Validated ArticleSaveForm articleSaveForm, BindingResult bindingResult, Model model, Principal principal){
        //principal은 시스템을 사용하려는 사용자, 시스템을 통칭한다.
        if(bindingResult.hasErrors()){
            return "user/article/write";
        }
        try {
            //멤버가 잘있으면 save를 통해 저장해주고 서비스로 보내주고 멤버가 없으면 에러를 발생시켜 write페이지로 다시 가게함

            Member findMember = memberService.findByLoginId(principal.getName());

            articleService.save(
                    articleSaveForm, findMember
            );
        }catch(IllegalStateException e){
            model.addAttribute("err_msg",e.getMessage());
            return "user/article/write";
        }
        return "redirect:/";
    }

    @GetMapping("/articles/modify/{id}")
    public String showModify(@PathVariable(name="id")Long id,Model model){
        try{
            Article article = articleService.getById(id);

            model.addAttribute("articleModifyForm",new ArticleModifyForm(article.getTitle(),article.getBody()));

            return "user/article/modify";
        }catch(Exception e){
            return "redirect:/";
        }
    }

    @PostMapping("/articles/modify/{id}")
    public String doModify(@PathVariable(name="id")Long id,ArticleModifyForm articleModifyForm){
        try{
            articleService.modifyArticle(articleModifyForm,id);
            return "redirect:/articles/"+ id;
        }
        catch(Exception e){
            return "user/article/modify";
        }
    }

    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable(name="id")Long id,Principal principal){ //principal은 현재 로그인한 사람을 의미
        try{
            ArticleDTO article = articleService.getArticle(id);
            
            if(article.getAuthorName()!= principal.getName()){
                return "redirect:/";
            }
            articleService.delete(id);
            return "redirect:/";
        }
        catch(Exception e){
            return "redirect:/";

        }
    }

    @GetMapping("/articles")
    public String showList(Model model){
        List<ArticleDTO> articleList = articleService.getArticleList();
        model.addAttribute("articleList",articleList);
        return "user/article/list";
    }

    @GetMapping("/articles/{id}")
    public String showDetail(@PathVariable(name="id")Long id,Model model){


        try {
            ArticleDTO findArticle = articleService.getArticle(id);
            model.addAttribute("article", findArticle);
            return "user/article/detail";   //게시물을 잘 찾았으면 detail로 리턴
        }catch(Exception e){
            return "redirect:/";
        }
    }



}




















