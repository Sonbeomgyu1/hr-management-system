package com.apple.shop.sales;

import com.apple.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 증가
    private Long id;

    private String itemName;
    private Integer price;
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name="member_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Member member; //member의 ID값

    @CreationTimestamp
    private LocalDateTime created;
}
