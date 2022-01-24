package com.pjh.food_cm.service;


import com.pjh.food_cm.DTO.article.ArticleListDTO;
import com.pjh.food_cm.DTO.board.BoardDTO;
import com.pjh.food_cm.DTO.board.BoardModifyForm;
import com.pjh.food_cm.DTO.board.BoardSaveForm;
import com.pjh.food_cm.dao.BoardRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardSaveForm boardSaveForm){
        Board board=Board.createBoard(
                boardSaveForm.getName(),
                boardSaveForm.getDetail()
        );

        boardRepository.save(board);
    }


    public List<Board> findAll() {   //단순히 조회목적이므로 트랜잭션을 할 필요 없음
        return boardRepository.findAll();
    }

    public Optional<Board> findbyId(Long id){   //id로 게시판을 찾는거
        return  boardRepository.findById(id);

    }

    public BoardDTO getBoardDetail(Long id){
        Optional<Board> boardOptional= findbyId(id);  //게시판이 존재하는지 안하는지를 확인하기 위한것

        //확인한 결과 해당 게시판이 존재하지 않으면
        boardOptional.orElseThrow(

                ()-> new NoSuchElementException("해당 게시판은 존재하지 않습니다.")
        );

        //게시판이 존재하면
        Board findBoard=boardOptional.get();

        List<ArticleListDTO> articleList=new ArrayList<>();
        List<Article> articles=findBoard.getArticles();

        for(Article article: articles){
            ArticleListDTO articleListDTO=new ArticleListDTO(article);
            articleList.add(articleListDTO);
        }

        return new BoardDTO(findBoard,articleList);

    }

    @Transactional
    public Long modify(BoardModifyForm boardModifyForm) throws NoSuchElementException{
        Optional<Board> boardOptional=boardRepository.findByName(boardModifyForm.getName());

        boardOptional.orElseThrow(
                ()-> new NoSuchElementException("해당 게시판은 존재하지 않습니다.")
        );

        Board board=boardOptional.get();

        board.modifyBoard(boardModifyForm.getName(), boardModifyForm.getDetail());

        return board.getId();
    }

    //게시판 삭제
    @Transactional
    public void delete(Long id){
        Optional<Board> boardOptional=findbyId(id);
        boardOptional.orElseThrow(
                ()->new NoSuchElementException("해당 게시물은 존재하지 않습니다.")
        );

        Board findBoard=boardOptional.get();

        boardRepository.delete(findBoard);
    }

}
















