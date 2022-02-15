package com.pjh.food_cm.controller;

import com.pjh.food_cm.service.ADMArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ADMArticleController {
    private final ADMArticleService admArticleService;

    //게시글 리스트
    @GetMapping("/articles")
    public String showManageArticle(Model model){

        model.addAttribute("articles",admArticleService.getArticleList());
        return "admin/article/main";
    }

    //게시글 디테일

    //게시글 삭제

}























