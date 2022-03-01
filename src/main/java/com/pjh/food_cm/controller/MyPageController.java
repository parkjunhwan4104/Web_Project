package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.member.myPageDTO;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    @GetMapping("/mypage/{loginId}")
    public String showMyPage(@PathVariable(name="loginId")String loginId, Model model, Principal principal){
        if(!principal.getName().equals(loginId)){
            return "redirect:/";

        }
        myPageDTO articles = memberService.getMyArticles(principal.getName());
        model.addAttribute("memberInfo",articles);
        model.addAttribute("loginMember",principal.getName());
        return "user/member/mypage";

    }
}















