package com.pjh.food_cm.config;

import com.pjh.food_cm.dao.MemberRepository;
import com.pjh.food_cm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@Component  //스프링을 사용하지않고 스프링부트만 사용할떄 사용하는 것, 클래스에 붙여져서 스프링이 돌아갈때 얘를 찾음
@RequiredArgsConstructor
public class Datainit {
    
    private final InitService initService;

    @PostConstruct //서버를 내렸다가 올렸을 때 bean이 뜨고 postConstruct 어노테이션이 있는 것부터 가장 먼저 실행됨=> 가장 먼저 관리자가 DB에 생성되도록함
    public void init(){
        initService.initAdmin();
    }

    @Component
    @Service
    @RequiredArgsConstructor
    static class InitService{
        private final MemberRepository memberRepository;

        public void initAdmin(){
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            Member admin=Member.createMember(
                    "admin",
                    bCryptPasswordEncoder.encode("admin"),
                    "관리자",
                    "관리자",
                    "admin@admin.com",
                    Role.ADMIN
            );

            memberRepository.save(admin);
        }
    }

}
























