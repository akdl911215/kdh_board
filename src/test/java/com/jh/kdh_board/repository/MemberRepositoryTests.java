package com.jh.kdh_board.repository;

import com.jh.kdh_board.board.entity.Member;
import com.jh.kdh_board.board.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .password("111")
                    .name("USER" + i)
                    .build();

            memberRepository.save(member);
        });
    }
}
