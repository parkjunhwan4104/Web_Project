package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.board.BoardSaveForm;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Board;
import com.pjh.food_cm.service.ArticleService;
import com.pjh.food_cm.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/boards/add")
    public String showAdd(Model model){
        model.addAttribute("boardSaveForm",new BoardSaveForm());
        return "user/board/add";

    }

    @PostMapping("/boards/add")
    public String doAdd( BoardSaveForm boardSaveForm){

        boardService.save(boardSaveForm);
        return "redirect:/";
    }

    @GetMapping("/boards")
    public String showBoardList(Model model){
        List<Board> boardList=boardService.findAll();
        model.addAttribute("boardList",boardList); // boardList를 html로 보내는 행위

        return "user/board/list";
    }
}
