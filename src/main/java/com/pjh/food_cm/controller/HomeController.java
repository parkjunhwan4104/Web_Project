package com.pjh.food_cm.controller;


import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {



    @GetMapping("/")
    public String showIndex(){




        return "index";
    }
}
