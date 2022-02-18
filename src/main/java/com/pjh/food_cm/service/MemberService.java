package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.article.ArticleDTO;
import com.pjh.food_cm.DTO.member.MemberModifyForm;
import com.pjh.food_cm.DTO.member.MemberSaveForm;
import com.pjh.food_cm.DTO.member.myPageDTO;
import com.pjh.food_cm.config.Role;
import com.pjh.food_cm.dao.MemberRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //쿼리가 다 날라가지 않고 읽을 때만?
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ArticleService articleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  memberRepository.findByLoginId(username).get();
    }

    /**
     * 회원 정보 중복 확인
     * @param loginId
     * @param nickname
     * @param email
     */

    public void isDuplicateMember(String loginId, String nickname,String email){
        if(memberRepository.existsByLoginId(loginId)){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
            
        }
        else if(memberRepository.existsByNickname(nickname)){
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
        else if(memberRepository.existsByEmail(email)){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
        
    }


    /**
     * 회원가입
     * @param memberSaveForm
     */
    @Transactional //회원정보를 추가하거나 수정하는 것은 트랜젝션을 걸어줘야함 ,detail 보는 거나 article조회는 추가로 필요없음
    public void save(MemberSaveForm memberSaveForm) throws IllegalStateException{
        
        isDuplicateMember(
                memberSaveForm.getLoginId(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail()
        );   //이것이 성립이 되면 save를 해서 회원가입의 정보가 저장이 됨

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        Member member= Member.createMember(
                memberSaveForm.getLoginId(),
                bCryptPasswordEncoder.encode(memberSaveForm.getLoginPw()),
                memberSaveForm.getName(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail(),
                Role.MEMBER
        );
        memberRepository.save(member);  //DB에서의 정보를 업데이트하는 것
    }
     public Member findByLoginId(String loginId) throws IllegalStateException{  //회원이 로그인한 아이디로 찾는거
        Optional<Member> memberOptional=memberRepository.findByLoginId(loginId);
        memberOptional.orElseThrow(     //로그인한 아이디가 존재하지 않을 떄의 대처
                () -> new IllegalStateException("존재하지 않는 회원입니다.")
        );
        return memberOptional.get();
     }

    public Member findById(Long id){  //회원 식별번호로 찾는거
        Optional<Member> byId = memberRepository.findById(id);

        byId.orElseThrow(
                ()-> new NoSuchElementException("해당 회원은 존재하지 않습니다.")
        );
        return byId.get();
    }
    public myPageDTO getMyArticles(String loginId){

        List<ArticleDTO> articleDTOList=new ArrayList<>();

        Member findMember = findByLoginId(loginId);

        List<Article> articles = findMember.getArticles();

        for(Article article:articles){
            ArticleDTO findArticle = articleService.getArticle(article.getId());
           articleDTOList.add(findArticle);
        }
        return new myPageDTO(findMember,articleDTOList);

    }

    /**
     * 회원정보 수정
     * @param memberModifyForm
     * @param loginId
     * @return
     */
    @Transactional
    public Long modifyMember(Long id,MemberModifyForm memberModifyForm, String loginId){
        Member member=findById(id);


        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();


        member.modifyMember(
                bCryptPasswordEncoder.encode(memberModifyForm.getLoginPw()),
                memberModifyForm.getNickname(),
                memberModifyForm.getEmail()
        );
        return member.getId();
    }


    public Member getMember(String loginId){
        Member member=findByLoginId(loginId);
        return member;
    }

    public boolean isDupleMember(String loginId){
        return memberRepository.existsByLoginId(loginId);
    }

    

}




















