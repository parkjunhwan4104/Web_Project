package com.pjh.food_cm.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String detail;

    private LocalDateTime regDate=LocalDateTime.now();
    private LocalDateTime updateDate=LocalDateTime.now();

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")  //보드 혼자만 어떤 회원인 게시물을 쓰는지 알수 있도록 하기 위해 1대1
    private Member member;

    @OneToMany(mappedBy = "board",fetch=FetchType.LAZY)  //cascade=CascadeType.REMOVE? 아닌가
    private List<Article> articles=new ArrayList<>();

    public static Board createBoard(String name,String detail,Member member){
        Board board=new Board();
        board.name=name;
        board.detail=detail;
        board.member=member;
        return board;
    }

    public void modifyBoard(String name,String detail){
        this.name=name;
        this.detail=detail;
        this.updateDate=LocalDateTime.now();

    }




}
