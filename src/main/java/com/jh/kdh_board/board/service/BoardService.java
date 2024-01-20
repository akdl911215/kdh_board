package com.jh.kdh_board.board.service;

import com.jh.kdh_board.board.dto.BoardDTO;
import com.jh.kdh_board.board.entity.Board;
import com.jh.kdh_board.board.entity.Member;

public interface BoardService {

    BoardDTO register(BoardDTO dto);


    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Board board = Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .contnet(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default BoardDTO entityToDto(Board entity){
        BoardDTO boardDTO = BoardDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContnet())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();

        return boardDTO;
    }

}
