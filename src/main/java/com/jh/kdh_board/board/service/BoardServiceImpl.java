package com.jh.kdh_board.board.service;

import com.jh.kdh_board.board.dto.BoardDTO;
import com.jh.kdh_board.board.dto.PageRequestDTO;
import com.jh.kdh_board.board.dto.PageResultDTO;
import com.jh.kdh_board.board.entity.Board;
import com.jh.kdh_board.board.entity.Member;
import com.jh.kdh_board.board.entity.Reply;
import com.jh.kdh_board.board.repository.BoardRepository;
import com.jh.kdh_board.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public BoardDTO register(BoardDTO dto) {

        log.info("register ....");

        Board board = dtoToEntity(dto);

        Board entity = boardRepository.save(board);

        BoardDTO convertEntity = entityToDto(entity);

        return convertEntity;
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDto2((Board) en[0], (Member) en[1], (Long) en[2]));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("id").descending())
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long id) {

        Object result = boardRepository.getBoardByBoardId(id);

        Object[] arr = (Object[]) result;

        return entityToDto2((Board)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public BoardDTO removeRelatedBoard(Long id) {

        // 삭제 기능 구현 // 트랜잭션 추가

        log.info("remove start");

        Optional<Board> boardResult = boardRepository.findById(id);
        LocalDateTime dateTime = LocalDateTime.now();

        if (boardResult.isPresent()) {
            Board boardEntity = boardResult.get();

            Optional<List<Reply>> replyResult = replyRepository.getListFindByBoardId(boardResult.get().getId());

            if (replyResult.isPresent()) {
                List<Reply> replyEntity = replyResult.get();

                replyEntity.forEach(value -> {
                    value.changeReplyDeletedAt(dateTime);
                    replyRepository.save(value);
                });
            }

            boardEntity.changeBoardDeletedAt(dateTime);
            Board convertBoardEntity = boardRepository.save(boardEntity);

            BoardDTO response = entityToDto(convertBoardEntity);

            return response;
        }

        return null;
    }

    @Override
    public BoardDTO modify(BoardDTO boardDTO) {

        log.info("modify start");

        Optional<Board> result = boardRepository.findById(boardDTO.getId());

        if (result.isPresent()) {
            Board board = result.get();
            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            Board entity = boardRepository.save(board);

            BoardDTO dto = entityToDto(entity);

            return dto;
        }

        return null;
    }
}
