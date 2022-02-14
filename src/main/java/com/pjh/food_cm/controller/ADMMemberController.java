package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.ADM.ADM_MemberDetailDTO;
import com.pjh.food_cm.DTO.member.MemberDTO;
import com.pjh.food_cm.service.ADM_MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ADMMemberController {
    private final ADM_MemberService adm_memberService;

    @GetMapping("/members")  //회원관리 페이지
    public String showManageMember(Model model){

        List<MemberDTO> members = adm_memberService.getMemberList();
        model.addAttribute("members",members);


        return "admin/member/main";
    }

    @GetMapping("/members/{id}/detail")
    public String showMemberDetail(@PathVariable(name="id")Long id,Model model){
        ADM_MemberDetailDTO memberDetail= adm_memberService.getMemberDetail(id);
        model.addAttribute("member",memberDetail);
        return "admin/member/detail";
    }

    @GetMapping("/members/ban/{id}")
    public String doBanMember(@PathVariable(name="id")Long id){
        adm_memberService.banMember(id);

        return "redirect:/admin/members";
    }

}

























































