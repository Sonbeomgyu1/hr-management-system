package com.apple.shop.jobkorea;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobkorea")
@Getter
@Setter
@ToString
public class JobKorea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String displayName;

    @Column(name = "JOB_KOREA_TYPE_CD", length = 30)
    private String jobKoreaTypeCd;

    @Column(name = "MANPOWER_ID")
    private Integer manpowerId;

    @Column(name = "SEARCH_DATE")
    private LocalDate searchDate;  // ⬅ 날짜 타입으로 변경

    @Column(name = "AREA", length = 100)
    private String area;

    @Column(name = "BIRTHDAY", length = 20)
    private String birthday;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "JOB_KOREA_ID", length = 100)
    private String jobKoreaId;

    @Column(name = "OUT_DATE", length = 30)
    private String outDate;

    @Column(name = "CONTACT_NUM", length = 100)
    private String contactNum;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "SKILL", length = 1000)
    private String skill;

    @Column(name = "CARRER", length = 50)
    private String carrer;

    @Column(name = "HOPE_PRICE", length = 50)
    private String hopePrice;

    @Column(name = "PROJECT_NM", length = 50)
    private String projectNm;

    @Column(name = "PROGRESS_DESC", length = 2000)
    private String progressDesc;

    @ManyToOne
    @JoinColumn(name = "member_id") // member 테이블의 id와 연결
    private com.apple.shop.member.Member member;  // java.lang.reflect.Member 대신 com.apple.shop.member.Member를 사용

    @Column(name = "last_modified")
    private LocalDateTime lastModified;
    // 업데이트 시 자동으로 수정된 시간 기록
    @PreUpdate
    public void updateLastModified() {
        this.lastModified = LocalDateTime.now();
    }
}
