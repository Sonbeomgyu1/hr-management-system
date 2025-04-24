package com.apple.shop.CustomerMember;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

import com.apple.shop.project.ProjectMember;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "customer_member")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 증가
    public Long id;

    @Column(nullable = false, length = 50)
    private String name; // 이름

    @Column(nullable = false, length = 100)
    private String region; // 지역

    @Column(nullable = false, length = 20)
    private String phoneNumber; // 전화번호

    @Column(nullable = false, length = 100)
    private String skill; // 기술

    @Column(nullable = false, length = 50)
    private String grade; // 등급

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date createdDate; // 입력한 날짜 (yyyy-MM-dd)

    @Column(name = "user_id") // 로그인한 사용자의 ID
    private Long userId;

   


}

