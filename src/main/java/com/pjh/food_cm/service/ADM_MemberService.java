package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.member.MemberDTO;
import com.pjh.food_cm.dao.MemberRepository;
import com.pjh.food_cm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ADM_MemberService {
    private final MemberRepository memberRepository;

    public List<MemberDTO> getMemberList(){

        List<MemberDTO> memberDTOList=new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();

        for(Member member:memberList){
            MemberDTO memberDTO=new MemberDTO(member);
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;

    }
}










































