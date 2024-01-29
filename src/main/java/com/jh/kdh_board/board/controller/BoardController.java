package com.jh.kdh_board.board.controller;

import com.jh.kdh_board.board.dto.BoardDTO;
import com.jh.kdh_board.board.dto.PageRequestDTO;
import com.jh.kdh_board.board.dto.PageResultDTO;
import com.jh.kdh_board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public PageResultDTO list(PageRequestDTO pageRequestDTO) {
        log.info("list : " + pageRequestDTO);

        PageResultDTO response = boardService.getList(pageRequestDTO);
        log.info("response : " + response);

        return response;
    }


    @PostMapping("/register")
    public BoardDTO register(BoardDTO boardDTO) {

        log.info("register start : " + boardDTO);

        BoardDTO response = boardService.register(boardDTO);
        log.info("response : " + response);

        return response;
    }

    @GetMapping("/register")
    public String register() {
        log.info("register get ...");

        return "register post please...";
    }

    @PatchMapping("/update")
    public BoardDTO update(@RequestBody BoardDTO boardDTO) {
        log.info("update boardDTO : " + boardDTO);
        BoardDTO response = boardService.modify(boardDTO);

        log.info("response : " + response);

        return response;
    }

    @PatchMapping("/remove")
    public BoardDTO remove(Long id) {
        log.info("remove id : " + id);

        BoardDTO boardDTO = boardService.removeRelatedBoard(id);
        log.info("boardDTO : " + boardDTO);

        return boardDTO;
    }

    @GetMapping("/read/{id}")
    public BoardDTO read(@PathVariable Long id) {
        log.info("read id : " + id);

        BoardDTO response = boardService.get(id);
        log.info("response : " + response);

        return response;
    }
}
