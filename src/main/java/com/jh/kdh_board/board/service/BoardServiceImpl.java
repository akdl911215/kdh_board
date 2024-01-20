package com.jh.kdh_board.board.service;

import com.jh.kdh_board.board.dto.BoardDTO;
import com.jh.kdh_board.board.entity.Board;
import com.jh.kdh_board.board.repository.BoardRepository;
import com.jh.kdh_board.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardDTO register(BoardDTO dto) {

        log.info("register ....");

        Board board = dtoToEntity(dto);

        Board entity = boardRepository.save(board);

        BoardDTO convertEntity = entityToDto(entity);

        return convertEntity;
    }
}
