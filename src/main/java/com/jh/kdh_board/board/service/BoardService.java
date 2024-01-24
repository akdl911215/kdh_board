package com.jh.kdh_board.board.service;

import com.jh.kdh_board.board.dto.BoardDTO;
import com.jh.kdh_board.board.dto.PageRequestDTO;
import com.jh.kdh_board.board.dto.PageResultDTO;
import com.jh.kdh_board.board.entity.Board;
import com.jh.kdh_board.board.entity.Member;

public interface BoardService {

    BoardDTO register(BoardDTO dto);

    // 목록 처리
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long id);

    BoardDTO removeRelatedBoard(Long id);

    BoardDTO modify(BoardDTO boardDTO);


    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Board board = Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default BoardDTO entityToDto(Board entity){
        BoardDTO boardDTO = BoardDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();

        return boardDTO;
    }

    default BoardDTO entityToDto2(Board board, Member member, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .deletedAt(board.getDeletedAt())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .replyCount(replyCount.intValue())
                .build();

        return boardDTO;
    }

}
