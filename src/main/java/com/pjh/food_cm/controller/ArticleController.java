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

        ArticleDTO findArticle = articleService.getArticle(id);


        model.addAttribute("boardName", findArticle.getBoardName());
        model.addAttribute("boardId",findArticle.getBoardId());
        model.addAttribute("articleSaveForm", new ArticleSaveForm());

        //return "user/article/write";
        return "user/article/write";
    }


    @PostMapping("/boards/{id}/articles/write")
    public String doWrite(@Validated ArticleSaveForm articleSaveForm, BindingResult bindingResult, Model model, Principal principal, @PathVariable(name = "id") Long id) {

        if (bindingResult.hasErrors()) {
            ArticleDTO findArticle = articleService.getArticle(id);


            model.addAttribute("boardName", findArticle.getBoardName());
            model.addAttribute("boardId",findArticle.getBoardId());
            return "user/article/write";
        }
        try {
            Board findBoard = boardService.getBoard(articleSaveForm.getBoard_id());
            Member findMember = memberService.findByLoginId(principal.getName());


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

            ArticleDTO findArticle=articleService.getArticle(id);

            model.addAttribute("boardName",findArticle.getBoardName());
            model.addAttribute("boardId",findArticle.getBoardId());
            model.addAttribute("articleId",findArticle.getId());
            model.addAttribute("articleModifyForm",new ArticleModifyForm(
                    findArticle.getTitle(),
                    findArticle.getBody(),
                    findArticle.getBoardId(),
                    findArticle.getBoardName()
            ));

            return "user/article/modify";
        }catch(Exception e){
            return "redirect:/";
        }
    }

    @PostMapping("/articles/modify/{id}")
    public String doModify(@PathVariable(name="id")Long id,@Validated ArticleModifyForm articleModifyForm,BindingResult bindingResult,Principal principal,Model model){

        if(bindingResult.hasErrors()){
            ArticleDTO findArticle=articleService.getArticle(id);
            model.addAttribute("boardName",findArticle.getBoardName());
            model.addAttribute("boardId",findArticle.getBoardId());
            model.addAttribute("articleId",findArticle.getId());

            return "user/article/modify";
        }

        try{


            ArticleDTO findArticle = articleService.getArticle(id);
            if(!findArticle.getMemberLoginId().equals(principal.getName())){   //?????? ????????? ???????????? ????????? ???????????? ???????????? ??????
                throw new IllegalStateException("????????? ???????????????."); //????????? ???????????? catch????????? ???????????????
            }

            Board findBoard=boardService.getBoard(articleModifyForm.getBoard_id());
            articleService.modifyArticle(articleModifyForm,findBoard,id);
            return "redirect:/boards/"+ id;
        }
        catch(Exception e){
            return "redirect:/articles/modify/"+id;
        }
    }

    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable(name="id")Long id,Principal principal){ //principal??? ?????? ???????????? ????????? ??????
        try{
            ArticleDTO article = articleService.getArticle(id);
            
            if(!article.getMemberLoginId().equals(principal.getName())){
                return "redirect:/boards/"+id;
            }
            articleService.delete(id);
            return "redirect:/boards";
        }
        catch(Exception e){
            return "redirect:/";

        }
    }

    @GetMapping("/articles")
    public String showList(Model model){
        List<ArticleDTO> articleList = articleService.getArticleList();

        ArticleDTO articleDTO=articleList.get(0); //???????????? ????????? ??????????????? ?????? ????????? 0??????????????? ?????????
        model.addAttribute("boardName",articleDTO.getBoardName());
        model.addAttribute("articleList",articleList);
        return "user/article/list";
    }

    @GetMapping("/articles/{id}")
    public String showDetail(@PathVariable(name="id")Long id,Model model){


        try {
            ArticleDTO findArticle = articleService.getArticle(id);
            model.addAttribute("article", findArticle);
            return "user/article/detail";   //???????????? ??? ???????????? detail??? ??????
        }catch(Exception e){
            return "redirect:/";
        }
    }



}




















