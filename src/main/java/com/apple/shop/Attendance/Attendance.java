package com.apple.shop.Attendance;

import com.apple.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 출근 시간
    private LocalDateTime checkIn;

    // 퇴근 시간
    private LocalDateTime checkOut;

    // 근태 유형: WORK, VACATION, SICK 등
    @Enumerated(EnumType.STRING)
    private AttendanceType type;

    @ManyToOne
    private Member member;
}