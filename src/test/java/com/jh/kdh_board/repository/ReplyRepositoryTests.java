package com.jh.kdh_board.repository;

import com.jh.kdh_board.board.entity.Board;
import com.jh.kdh_board.board.entity.Reply;
import com.jh.kdh_board.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long board_id = (long)(Math.random() * 100) + 1;

            Board board = Board.builder().id(board_id).build();

            Reply reply = Reply.builder()
                    .text("Reply" + i)
                    .board(board)
                    .replyer("guest" + i)
                    .build();

            replyRepository.save(reply);
        });
    }
}
