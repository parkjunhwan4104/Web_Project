package com.pjh.food_cm.controller;


import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;


    @GetMapping("/")
    public String showIndex(){


        return "index";
    }
}
