package com.jh.kdh_board.repository;

import com.jh.kdh_board.board.entity.Board;
import com.jh.kdh_board.board.entity.Member;
import com.jh.kdh_board.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    @Test
    public void testRead1() {
        Optional<Board> result = boardRepository.findById(100L); // 데이터에 존재하는 번호

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .build();

            Board board = Board.builder()
                    .title("Title" + i)
                    .contnet("Content" + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }
}
