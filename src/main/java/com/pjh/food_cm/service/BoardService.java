package com.pjh.food_cm.service;


import com.pjh.food_cm.DTO.board.BoardSaveForm;
import com.pjh.food_cm.dao.BoardRepository;
import com.pjh.food_cm.domain.Article;
import com.pjh.food_cm.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
}
