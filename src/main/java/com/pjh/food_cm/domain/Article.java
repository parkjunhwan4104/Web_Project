package com.pjh.food_cm.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter //값을 다른곳에서 사용할수 있도록 즉 articleDTO에서 사용할수 있도록
@NoArgsConstructor(access= AccessLevel.PROTECTED)  //무분별하게 객체생성을 막기위해
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;
    private LocalDateTime regDate=LocalDateTime.now();
    private LocalDateTime updateDate=LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)  //지연로딩이라고 하며 엔티티 내에 연관관계를 참조할때 해당 연관관계에 해당하는 것이 사용되는 시점에만 연관된 엔티티(member)의 데이터를 조회하는거임, 실무에서는 주로 지연로딩만 사용함
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    //생성 메소드
    public static Article createArticle(String title,String body){
        Article article=new Article();
        article.title=title;
        article.body=body;
        return article;
    }

    public void modifyArticle(String title,String body){
        this.title=title;
        this.body=body;
        this.updateDate=LocalDateTime.now();
    }


    public void setMember(Member member){
        this.member=member;
        member.getArticles().add(this);

    }

    public void setBoard(Board board){
        this.board=board;
        board.getArticles().add(this);
    }















    
}
