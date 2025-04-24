package com.apple.shop.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project")
@Getter
@Setter 
@NoArgsConstructor
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 프로젝트 이름
    private String description; // 프로젝트 설명

    private LocalDate startDate;
    private LocalDate endDate;

    private Long budget; // 예산

    @Column(name = "user_id") // 만든 사람 ID
    private Long userId;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectMember> members = new ArrayList<>();
}