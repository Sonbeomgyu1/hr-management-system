package com.apple.shop.contract;

import com.apple.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String filePath; // 실제 파일 저장 경로
    private String originalFileName;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(name = "notified")
    private boolean notified;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 계약 대상 (고객 혹은 사원 등)
    
    public String getFileNameOnly() {
        return filePath != null ? filePath.substring(filePath.lastIndexOf(File.separator) + 1) : "";
    }

}
