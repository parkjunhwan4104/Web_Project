package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.member.MemberLoginForm;
import com.pjh.food_cm.DTO.member.MemberModifyForm;
import com.pjh.food_cm.DTO.member.MemberSaveForm;
import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원가입 페이지 이동
     * @param model
     * @return
     */
    @GetMapping("/members/join")
    public String showJoin(Model model){   //model은 html로 값을 넘겨줄수있는거
        model.addAttribute("memberSaveForm",new MemberSaveForm());  //memberSaveForm 이라는 이름으로 생성한 MemberSaveForm 객체를 추가한다.
        return "user/member/join";
    }


    /**
     * 회원가입 수행
     * @param memberSaveForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/members/join")
    public String doJoin(@Validated MemberSaveForm memberSaveForm, BindingResult bindingResult,Model model){  //Java에서는 null 값에 대해서 접근 하려고 할 때 null pointer exception이 발생함으로, 이러한 부분을 방지하기 위해서 미리 검증을 하는 과정이 validataed 어노테이션임
        if(bindingResult.hasErrors()){
            return "user/member/join";  //에러가 존재하면 다시 회원가입 화면을 보여주겠다는 거
        }

        try{
            memberService.save(memberSaveForm);
        }
        catch (Exception e){
            model.addAttribute("err_msg",e.getMessage());

        }
        return "redirect:/"; //로그인이 성공되었을 때 이동해야하는 주소
    }

    @GetMapping("/members/login")
    public String showLogin(Model model){
        model.addAttribute("memberLoginForm",new MemberLoginForm());
        return "user/member/login";
    }

    @GetMapping("/members/modify/{id}")
    public String showModify(Model model, Principal principal,@PathVariable(name="id")Long id){



        Member findMember = memberService.findById(id); //지금 로그인한 회원이 누군지 알 수 있음

        if(!findMember.getLoginId().equals(principal.getName())){
            return "redirect:/";
        }

        model.addAttribute("memberModifyForm",new MemberModifyForm(
                findMember
        ));
        return "user/member/modify";
    }

    @PostMapping("/members/modify/{id}")
    public String doModify(@PathVariable(name="id")Long id,Principal principal,Model model,@Validated MemberModifyForm memberModifyForm,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/member/modify";
        }

        Member findMember=memberService.findById(id);

        if(!findMember.getLoginId().equals(principal.getName())){
            return "redirect:/";
        }

       try {

           memberService.modifyMember(id,memberModifyForm, principal.getName());
       }
       catch(Exception e){
           model.addAttribute("err_msg",e.getMessage());
           model.addAttribute("memberModifyForm",new MemberModifyForm(
                   findMember
           ));
           return "user/member/modify";
       }
       return "redirect:/";

    }

}
