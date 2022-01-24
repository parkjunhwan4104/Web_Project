package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.board.BoardDTO;
import com.pjh.food_cm.DTO.board.BoardModifyForm;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")  // 자동적으로 /admin이 url에 앞에 붙게됨 즉 아래에서 보면 /admin/boards/add 가 된다는거
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/boards/add")
    public String showAdd(Model model){
        model.addAttribute("boardSaveForm",new BoardSaveForm());
        return "admin/board/add";

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

        return "admin/board/list";
    }

    @GetMapping("/boards/{id}")
    public String showBoardDetail(@PathVariable(name="id")Long id,Model model){

        try {
            BoardDTO boardDetail = boardService.getBoardDetail(id);
            model.addAttribute("board", boardDetail); //html에서 board라는 이름으로 boardDetail값이 들어가는거
        }
        catch(Exception e){
            return "redirect:/";
        }
        return "admin/board/detail";
    }

    @GetMapping("/boards/modify")
    public String showModifyBoard(Model model){
        model.addAttribute("boardModifyForm",new BoardModifyForm());
        return "admin/board/modify";
    }

    @PostMapping("/boards/modify")
    public String doModifyBoard(BoardModifyForm boardModifyForm){

        try{
            boardService.modify(boardModifyForm);
        }
        catch(Exception e){
            return "admin/board/modify";
        }
        return "redirect:/";
    }

    @GetMapping("/boards/delete/{id}")
    public String doDeleteBoard(@PathVariable(name="id") Long id){
        try{
            boardService.delete(id);    //보드 서비스의 delete로 가서 해당 게시판이 존재하지 않으면 에러를 catch함
            return "admin/board/list";   
        }
        catch (Exception e){
            return "admin/board/list";
        }
    }




}
















