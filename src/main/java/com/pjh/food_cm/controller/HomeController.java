package com.pjh.food_cm.controller;


import com.pjh.food_cm.DTO.board.BoardDTO;
import com.pjh.food_cm.DTO.board.BoardListDTO;
import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.ArticleService;
import com.pjh.food_cm.service.BoardService;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleService articleService;
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/")
    public String showIndex(Model model){

        List<BoardListDTO> boardListDTOList=boardService.getBoardList();

        for(int i=1; i<=boardListDTOList.size();i++){
            Long xLong=Long.valueOf(i);
            BoardDTO boardDTO=boardService.getBoardDetail(xLong);
            model.addAttribute("board"+xLong,boardDTO);
        }

        //model.addAttribute("boardList",boardListDTOList);
        return "index";
    }
}
