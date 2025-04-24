package com.apple.shop.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 증가
    private Long id;

    @Column
    private String username;

    @Column(length = 1000)
    private String content;

    private Long parentId;

    @Column(nullable = false)
    private String type;  // "customer", "post", "product" 등 댓글의 대상 유형

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // 댓글이 생성된 날짜 및 시간
}
