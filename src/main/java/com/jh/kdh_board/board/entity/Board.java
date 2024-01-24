package com.jh.kdh_board.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @ManyToOne (fetch = FetchType.LAZY)
    private Member writer; // 연관관계 지정

    public void changeBoardDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }
    public void changeTitle(String title) { this.title = title; }
    public void changeContent(String content) { this.content = content; }
}
