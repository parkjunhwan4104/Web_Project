package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.ADM.ADM_MemberDetailDTO;
import com.pjh.food_cm.DTO.article.ArticleListDTO;
import com.pjh.food_cm.DTO.member.MemberDTO;
import com.pjh.food_cm.dao.MemberRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        }   //여기서 그냥 articles를 쓰지않고 굳이 articleListDto의 리스트를 만들어서 articles에 해당하는 객체들을 하나하나 넣어 준 이유는 
           // articles에는 많은 정보가 들어있는데 이 중에 articles에서 모든 데이터를 조회하는 것이 아니라 articleListDto에서 정의한 , 즉 articles에서 필요한 데이터만을 가져오기 위해 이러한 코드가 작성된거임
        return new ADM_MemberDetailDTO(findMember,list);
    }

    @Transactional
    public void banMember(Long id){
        Optional<Member> memberOptional = memberRepository.findById(id);

        memberOptional.orElseThrow(
                ()-> new IllegalStateException("존재하지 않는 회원입니다.")
        );

        memberRepository.delete(memberOptional.get());
    }

}










































