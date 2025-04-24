package com.apple.shop.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    
	List<ProjectMember> findByProject_Id(Long projectId);
}
