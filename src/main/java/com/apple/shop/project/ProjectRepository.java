package com.apple.shop.project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // 기본 CRUD 기능은 JpaRepository에서 제공
}
