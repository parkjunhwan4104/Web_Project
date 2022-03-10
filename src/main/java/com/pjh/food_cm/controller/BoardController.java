package com.pjh.food_cm.controller;

import com.pjh.food_cm.DTO.article.ArticleListDTO;
import com.pjh.food_cm.DTO.board.BoardDTO;
import com.pjh.food_cm.DTO.board.BoardModifyForm;
import com.pjh.food_cm.DTO.board.BoardSaveForm;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Board;
import com.pjh.food_cm.domain.Member;
import com.pjh.food_cm.service.ArticleService;
import com.pjh.food_cm.service.BoardService;
import com.pjh.food_cm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
// @RequestMapping("/admin")   자동적으로 /admin이 url에 앞에 붙게됨 즉 아래에서 보면 /admin/boards/add 가 된다는거
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService; //게시판에서 어느 회원이 글을 썼는지 알 수 있게 사용해야함



    @GetMapping("/boards")
    public String showBoardList(Model model){
        List<Board> boardList=boardService.findAll();
        model.addAttribute("boardList",boardList); // boardList를 html로 보내는 행위

        return "admin/board/list";
    }

    @GetMapping("/boards/{id}")
    public String showBoardDetail(@PathVariable(name="id")Long id, Model model, @RequestParam(name="page",defaultValue = "1") int page,@RequestParam(name="searchKeyword",defaultValue ="")String searchKeyword){

        int size =10;


        try {
            BoardDTO boardDetail = boardService.getBoardDetail(id);

            List<ArticleListDTO> articleListDTO=boardDetail.getArticleListDTO();

            List<ArticleListDTO> store=new ArrayList<>();

            for(ArticleListDTO listDTO:articleListDTO){
                if(listDTO.getTitle().contains(searchKeyword)){
                    store.add(listDTO);
                }
            }

            if(store.size()!=0){
                for(ArticleListDTO listDTO:store){
                    articleListDTO=store;
                }
            }

            Collections.reverse(articleListDTO);

            // 한 페이지에서의 인덱스
            int startIndex=(page-1)*size;
            int lastIndex = ((page -1) * size) + 9;

            //마지막 페이지 기준
            int lastPage=(int)Math.ceil(articleListDTO.size()/(double)size);

            
            if(page==lastPage){
                lastIndex= articleListDTO.size();
                
            }
            else if(page> lastPage){
                return "redirect:/";
            }
            else{
                lastIndex+=1;
            }

            //페이지 자르기
            List<ArticleListDTO> articlePage= articleListDTO.subList(startIndex, lastIndex); //어디서부터 자르고 어디까지 자를 건지( startIndex가 0이고 lastIndex가 9이면 0~8까지만 잘라짐 따라서 위에서 lastIndex에 1을 더해준거

            if(!searchKeyword.equals("")&& store.size()==0){
                articlePage=store;
            }

            model.addAttribute("board", boardDetail); //html에서 board라는 이름으로 boardDetail값이 들어가는거
            model.addAttribute("articles",articlePage);
            model.addAttribute("maxPage",lastPage);
            model.addAttribute("currentPage",page);
            model.addAttribute("keyword",searchKeyword);
        }
        catch(Exception e){
            return "redirect:/";
        }
        return "admin/board/detail";
    }





}
















