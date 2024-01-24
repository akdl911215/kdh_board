package com.jh.kdh_board.board.repository;

import com.jh.kdh_board.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, String> {

    @Query("SELECT r FROM Reply r WHERE r.board.id = :boardId")
    Optional<List<Reply>> getListFindByBoardId(Long boardId);
}
