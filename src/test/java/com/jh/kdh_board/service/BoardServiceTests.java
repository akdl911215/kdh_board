package com.jh.kdh_board.service;

import com.jh.kdh_board.board.dto.BoardDTO;
import com.jh.kdh_board.board.dto.PageRequestDTO;
import com.jh.kdh_board.board.dto.PageResultDTO;
import com.jh.kdh_board.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(2L)
                .title("Change Title...")
                .content("Change Content")
                .build();

        BoardDTO response = boardService.modify(boardDTO);
        System.out.println("response : " + response);
    }

    @Test
    public void testRemove() {
        Long id = 1L;
        boardService.removeRelatedBoard(id);
    }

    @Test
    public void testGet() {

        Long id = 1L;

        BoardDTO boardDTO = boardService.get(id);

        System.out.println(boardDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO: result.getDtoList()) {
            System.out.println(boardDTO);
        }
    }

    @Test
    public void testRegister() {
        BoardDTO dto = BoardDTO.builder()
                .title("Test!!!")
                .content("Content!!!")
                .writerEmail("user100@naver.com")
                .build();

        BoardDTO response = boardService.register(dto);
        System.out.println("response : " + response);
    }
}
