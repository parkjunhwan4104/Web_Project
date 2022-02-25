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


    //클라이언트에서 서버로 필요한 데이터를 요청하기 위해 JSON 데이터를 요청 본문에 담아서 서버로 보내면, 서버에서는 @RequestBody 어노테이션을 사용하여 HTTP 요청 본문에 담긴 값들을 자바객체로 변환시켜, 객체에 저장한다.
    //서버에서 클라이언트로 응답 데이터를 전송하기 위해 @ResponseBody 어노테이션을 사용하여 자바 객체를 HTTP 응답 본문의 객체로 변환하여 클라이언트로 전송한다.
    
    @ResponseBody
    @PostMapping("mails/find/pw")
    public boolean getForgotPassword(@RequestBody FindPasswordForm findPasswordForm){   //findPasswordForm에서 양식을 작성후 서버로 보내면 서버에서 RequestBody을 통해 자바객체로 만들어 저장후
        if(!memberService.isDupleLoginId(findPasswordForm.getLoginId())){               //서버에서 클라이언트로 보내주기 위해서 ResponseBody을 통해 클라이언트를 위한 http 응답객체로 변환해 클라이언트로 보내는 형식
            return false;
        }
        try{
            mailService.sendMail(findPasswordForm);

        }
        catch(Exception e){
            e.printStackTrace();
            return false;

        }
        return true;
    }
}
