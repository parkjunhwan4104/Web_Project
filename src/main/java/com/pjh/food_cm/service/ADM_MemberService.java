package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.ADM.ADM_MemberDetailDTO;
import com.pjh.food_cm.DTO.article.ArticleListDTO;
import com.pjh.food_cm.DTO.member.MemberDTO;
import com.pjh.food_cm.dao.MemberRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ADM_MemberDetailDTO getMemberDetail(Long id){
        Optional<Member> memberOptional = memberRepository.findById(id);

        memberOptional.orElseThrow(
                ()->new IllegalStateException("존재하지 않는 회원입니다.")
        );
        Member findMember=memberOptional.get();
        List<Article> articles = findMember.getArticles();

        List<ArticleListDTO> list=new ArrayList<>();

        for(Article article:articles){
            ArticleListDTO articleListDTO=new ArticleListDTO(article);
            list.add(articleListDTO);
        }
        return new ADM_MemberDetailDTO(findMember,list);
    }


}










































