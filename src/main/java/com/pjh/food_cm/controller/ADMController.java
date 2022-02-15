package com.pjh.food_cm.controller;


import com.pjh.food_cm.service.ADMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class ADMController {
    private final ADMService admService;  //final이 붙으면 초기화를 해줘야하는데  @RequiredArgsConstructor은 final이 붙은 것을 찾아서 생성자를 자동적으로 만들어줌

    @GetMapping("/page")
    public String showAdminPage(){
        return "admin/general/main";
    }






}











































