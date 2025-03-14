package com.apple.shop.calendar;

import com.apple.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class EventMemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String displayName;

    @ManyToOne
    @JoinColumn(name = "member_id") // member 테이블의 id와 연결
    private Member member;

    private String memo;
}