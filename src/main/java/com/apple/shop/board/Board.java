package com.apple.shop.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 증가
    public Long id;

    private String boardtitle;
    private String boardcontent;
    @Column(updatable = false)
    private LocalDateTime boarddate; // 날짜 추가

    @PrePersist
    protected void onCreate() {
        this.boarddate = LocalDateTime.now(); // 자동으로 현재 시간 저장
    }
}
