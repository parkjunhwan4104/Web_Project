package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.member.FindPasswordForm;
import com.pjh.food_cm.service.MailService;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MailController
{

    private final MailService mailService;
    private final MemberService memberService;

    @ResponseBody
    @PostMapping("mails/find/pw")
    public boolean getForgotPassword(@RequestBody FindPasswordForm findPasswordForm){
        if(!memberService.isDupleLoginId(findPasswordForm.getLoginId())){
            return false;
        }
        try{
            mailService.sendMail(findPasswordForm);

        }
        catch(Exception e){
            return false;

        }
        return false;
    }
}
