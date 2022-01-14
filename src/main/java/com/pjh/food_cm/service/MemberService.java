package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.member.MemberSaveForm;
import com.pjh.food_cm.config.Role;
import com.pjh.food_cm.dao.MemberRepository;
import com.pjh.food_cm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //쿼리가 다 날라가지 않고 읽을 때만?
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(username).get();
    }

    /**
     * 회원 정보 중복 확인
     * @param loginId
     * @param nickname
     * @param email
     */

    public void isDuplicateMember(String loginId, String nickname,String email){
        if(memberRepository.existByLoginId(loginId)){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
            
        }
        else if(memberRepository.existByNickName(nickname)){
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
        else if(memberRepository.existByEmail(email)){
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
        memberRepository.save(member);
    }

}
