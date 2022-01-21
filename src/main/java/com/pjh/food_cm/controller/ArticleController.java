package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.article.ArticleSaveForm;
import com.pjh.food_cm.dao.ArticleRepository;
import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.ArticleService;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

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


}




















