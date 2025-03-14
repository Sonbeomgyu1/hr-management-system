package com.apple.shop.manpower;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "manpower")
@Getter
@Setter
@ToString
public class ManPower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key (Auto Increment)

    private String displayName;

    @Column(nullable = false, length = 100)
    private String name; // Name (이름)

    @Column(name = "job_position", length = 100)
    private String jobPosition; // Job Position (직책/직무)

    @Column(length = 100)
    private String birthday; // Birthday (생년월일, VARCHAR 타입)
    
    @Column(name = "jobkoreaId")
    private String jobkoreaId; //잡코리아 아이디

    @Column(name = "contact_number", length = 20)
    private String contactNumber; // Contact Number (연락처)

    @Column(length = 255)
    private String email; // Email Address (이메일)

    @Column(name = "search_date")
    private LocalDate searchDate; // Search Date (검색 날짜)

    @Column(name = "work_area", length = 100)
    private String workArea; // Work Area (근무 가능 지역)

    @Column(name = "career_years", length = 100)
    private String careerYears; // Career Years (경력, VARCHAR 타입)

    @Column(name = "education_level", length = 50)
    private String educationLevel; // Education Level (학력 수준)

    @Column(columnDefinition = "TEXT")
    private String remark; // General Remark (일반 메모)

    @Column(columnDefinition = "TEXT")
    private String proposal; // Proposal Info (제안 관련 정보)

    @Column(columnDefinition = "TEXT")
    private String certifications; // Certifications (보유 자격증 목록)

    @Column(columnDefinition = "TEXT")
    private String skills; // Skills (기술 스택)

    @Column(name = "additional_note", columnDefinition = "TEXT")
    private String additionalNote; // Additional Remark (추가 메모)

    @Column(columnDefinition = "TEXT")
    private String evaluation; // Evaluation (평가 사항)

    @Column(columnDefinition = "TEXT")
    private String projects; // Projects (참여 프로젝트 정보)

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt; // Created Timestamp (생성 일시)

    @Column(name = "updated_at")
    private LocalDate updatedAt; // Updated Timestamp (수정 일시)

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
