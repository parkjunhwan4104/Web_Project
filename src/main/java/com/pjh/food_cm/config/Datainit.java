package com.pjh.food_cm.config;

import com.pjh.food_cm.dao.ArticleRepository;
import com.pjh.food_cm.dao.BoardRepository;
import com.pjh.food_cm.dao.MemberRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Board;
import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Component  //스프링을 사용하지않고 스프링부트만 사용할떄 사용하는 것, 클래스에 붙여져서 스프링이 돌아갈때 얘를 찾음
@RequiredArgsConstructor
public class Datainit {
    
    private final InitService initService;

    @PostConstruct //서버를 내렸다가 올렸을 때 bean이 뜨고 postConstruct 어노테이션이 있는 것부터 가장 먼저 실행됨=> 가장 먼저 관리자가 DB에 생성되도록함
    public void init(){
        initService.initAdmin();
        initService.initMember();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final MemberRepository memberRepository;
        private final BoardRepository boardRepository;
        private final ArticleRepository articleRepository;

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

            for(int i=1;i<=3;i++) {
                Board board = Board.createBoard(
                        "게시판"+i,
                        "게시판"+i,
                        admin



                );
                boardRepository.save(board);
            }
        }

        public void initMember(){
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

            List<Board> boardList = boardRepository.findAll();
            for(int i=1;i<=4;i++){
                Member member=Member.createMember(
                        "user"+i,
                        bCryptPasswordEncoder.encode("user"+i),
                        "user"+i,
                        "user"+i,
                        "user"+i+"@user.com",
                        Role.MEMBER
                );
                memberRepository.save(member);

                for(int k=0;k<=2;k++) {
                    for (int j = 1; j <= 3; j++) {
                        Article article = Article.createArticle(
                                "제목" + j,
                                "내용" + j
                        );
                        article.setMember(member);
                        article.setBoard(boardList.get(k));
                        articleRepository.save(article);
                    }
                }


            }



        }




    }

}
























