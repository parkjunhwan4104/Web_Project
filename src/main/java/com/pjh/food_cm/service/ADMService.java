package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.ADM.MemberStateDto;
import com.pjh.food_cm.config.Role;
import com.pjh.food_cm.dao.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ADMService {

    private final MemberRepository memberRepository;


    public MemberStateDto getMemberStateDto(){

        return new MemberStateDto(
                memberRepository.count(),
                memberRepository.countTodayMember(),
                memberRepository.countByAuthorityLike(Role.ADMIN),
                memberRepository.countByAuthorityLike(Role.MEMBER)
        );
    }



}






























