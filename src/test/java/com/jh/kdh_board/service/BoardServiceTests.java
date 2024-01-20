package com.jh.kdh_board.service;

import com.jh.kdh_board.board.dto.BoardDTO;
import com.jh.kdh_board.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

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
