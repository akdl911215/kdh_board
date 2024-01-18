package com.jh.kdh_board.board.repository;

import com.jh.kdh_board.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}