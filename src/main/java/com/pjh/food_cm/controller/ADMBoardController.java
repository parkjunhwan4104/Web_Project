package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.board.BoardDTO;
import com.pjh.food_cm.DTO.board.BoardModifyForm;
import com.pjh.food_cm.DTO.board.BoardSaveForm;
import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.BoardService;
import com.pjh.food_cm.service.MemberService;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ADMBoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    //게시판 리스트
    @GetMapping("/boards")
    public String showManageBoard(Model model){
        model.addAttribute("boards",boardService.getBoardList());
        return "admin/board/main";
    }


    //게시판 저장
    @GetMapping("/boards/add")
    public String showAdd(Model model){


        model.addAttribute("boardSaveForm",new BoardSaveForm());
        return "admin/board/add";

    }

    @PostMapping("/boards/add")
    public String doAdd(@Validated BoardSaveForm boardSaveForm, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            return "admin/board/add";
        }
        Member findAdmin=memberService.findByLoginId(principal.getName());
        boardService.save(boardSaveForm,findAdmin);
        return "redirect:/admin/boards";
    }


    //게시판 수정
    @GetMapping("/boards/modify/{id}")  //어떠한 게시판을 수정할지
    public String showModifyBoard(@PathVariable(name="id") Long id, Model model){

        try{
            BoardDTO board = boardService.getBoardDetail(id);

            model.addAttribute("boardId",board.getId());
            model.addAttribute("boardModifyForm",new BoardModifyForm(
                    board.getId(),
                    board.getName(),
                    board.getDetail()
            ));
            return "admin/board/modify";
        }catch(Exception e){
            return "redirect:/admin/boards";
        }

    }

    @PostMapping("/boards/modify/{id}")  //어떠한 게시판을 수정할지
    public String doModifyBoard(@PathVariable (name="id") Long id,@Validated BoardModifyForm boardModifyForm,BindingResult bindingResult,Model model){

        BoardDTO findBoard = boardService.getBoardDetail(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("boardId",findBoard.getId());
            return "admin/board/modify";
        }

        try{
            boardService.modify(id,boardModifyForm);
        }
        catch(Exception e){
            return "admin/board/modify";
        }
        return "redirect:/admin/boards";
    }

    //게시판 삭제
    @GetMapping("/boards/delete/{id}")
    public String doDeleteBoard(@PathVariable(name="id") Long id){
        try{
            boardService.delete(id);    //보드 서비스의 delete로 가서 해당 게시판이 존재하지 않으면 에러를 catch함

        }
        catch (Exception e){
            return "admin/board/list";
        }
        return "redirect:/admin/boards";
    }
}
















