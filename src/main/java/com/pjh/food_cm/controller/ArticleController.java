package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.article.ArticleDTO;
import com.pjh.food_cm.DTO.article.ArticleModifyForm;
import com.pjh.food_cm.DTO.article.ArticleSaveForm;
import com.pjh.food_cm.DTO.board.BoardDTO;
import com.pjh.food_cm.dao.ArticleRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Board;
import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.ArticleService;
import com.pjh.food_cm.service.BoardService;
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
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/boards/{id}/articles/write")
    public String showArticleWrite(@PathVariable(name = "id") Long id, Model model) {

        BoardDTO boardDetail = boardService.getBoardDetail(id);


        model.addAttribute("boardDetail", boardDetail);

        model.addAttribute("articleSaveForm", new ArticleSaveForm());

        //return "user/article/write";
        return "user/article/write";
    }


    @PostMapping("/boards/{id}/articles/write")
    public String doWrite(@Validated ArticleSaveForm articleSaveForm, BindingResult bindingResult, Model model, Principal principal, @PathVariable(name = "id") Long id) {

        if (bindingResult.hasErrors()) {
            return "user/article/write";
        }
        try {

            Member findMember = memberService.findByLoginId(principal.getName());
            Board findBoard = boardService.getBoard(articleSaveForm.getBoardId());

            articleService.save(
                    articleSaveForm,
                    findMember,
                    findBoard
            );

        } catch (IllegalStateException e) {

            model.addAttribute("err_msg", e.getMessage());


            return "user/article/write";

        }


        return "redirect:/articles";
    }

    @GetMapping("/articles/modify/{id}")
    public String showModify(@PathVariable(name="id")Long id,Model model){
        try{

            ArticleDTO article=articleService.getArticle(id);

            model.addAttribute("articleModifyForm",new ArticleModifyForm(article.getTitle(),article.getBody(), article.getBoardId()));

            return "user/article/modify";
        }catch(Exception e){
            return "redirect:/";
        }
    }

    @PostMapping("/articles/modify/{id}")
    public String doModify(@PathVariable(name="id")Long id,ArticleModifyForm articleModifyForm){
        try{
            Board findBoard=boardService.getBoard(id);
            articleService.modifyArticle(articleModifyForm,findBoard,id);
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

        ArticleDTO articleDTO=articleList.get(0); //게시판의 이름을 알아오기만 하면 되므로 0번인덱스로 해도됨
        model.addAttribute("boardName",articleDTO.getBoardName());
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




















