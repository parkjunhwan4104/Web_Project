package com.pjh.food_cm.service;

import com.pjh.food_cm.DTO.ADM.BoardStateDTO;
import com.pjh.food_cm.DTO.ADM.MemberStateDto;
import com.pjh.food_cm.DTO.ADM.admBoardCountDto;
import com.pjh.food_cm.DTO.ADM.admBoardNameDto;
import com.pjh.food_cm.config.Role;
import com.pjh.food_cm.dao.BoardRepository;
import com.pjh.food_cm.dao.MemberRepository;
import com.pjh.food_cm.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ADMService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public MemberStateDto getMemberStateDto(){

        return new MemberStateDto(
                memberRepository.count(),
                memberRepository.countTodayMember(),
                memberRepository.countByAuthorityLike(Role.ADMIN),
                memberRepository.countByAuthorityLike(Role.MEMBER)
        );
    }

    public BoardStateDTO getBoardStateDto(){

        List<Board> findLatestBoards = boardRepository.find3LatestBoard();

        List<admBoardNameDto> latestBoardList = new ArrayList<>();
        List<admBoardCountDto> boardCountList = new ArrayList<>();

        for( Board findLatestBoard : findLatestBoards ){

            admBoardNameDto BoardNameDto = new admBoardNameDto(findLatestBoard);
            latestBoardList.add(BoardNameDto);

        }

        List<Board> findAll = boardRepository.findAll();

        for( Board board : findAll ){

            admBoardCountDto BoardCountDto = new admBoardCountDto(board);
            boardCountList.add(BoardCountDto);

        }

        return new BoardStateDTO(boardRepository.count(), boardCountList, latestBoardList);



    }



}






























