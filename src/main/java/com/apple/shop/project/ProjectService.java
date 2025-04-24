package com.apple.shop.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProjectMemberRepository projectMemberRepository;
	
	//프로젝트생성
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}
	
	// 프로젝트 전체 조회
	public List<Project> getAllProjects() {
	    return projectRepository.findAll();
	}

	//프로젝트 상세 조회
	public Optional<Project> getProjectById(Long projectId){
		return projectRepository.findById(projectId);
	}
	
	//프로젝트 업데이트
	public boolean updateProject(Long projectId, Project updatedProject) {
	    Optional<Project> existingProject = projectRepository.findById(projectId);
	    if (existingProject.isPresent()) {
	        Project project = existingProject.get();
	        project.setName(updatedProject.getName());
	        project.setDescription(updatedProject.getDescription());
	        project.setStartDate(updatedProject.getStartDate());
	        project.setEndDate(updatedProject.getEndDate());
	        project.setBudget(updatedProject.getBudget());
	        projectRepository.save(project); // 수정된 프로젝트 저장
	        return true;
	    }
	    return false;
	}

	
	//프로젝트 삭제
	public void deleteProject(Long projectId) {
		projectRepository.deleteById(projectId);
	}
	
	 // 프로젝트 멤버 추가
	public ProjectMember addMemberToProject(Long projectId, ProjectMember member) {
	    // 로그 추가
	    System.out.println("프로젝트 ID: " + projectId);
	    System.out.println("추가할 멤버: " + member);

	    // 해당 프로젝트를 찾아서 멤버를 추가
	    Project project = projectRepository.findById(projectId)
	            .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다. ID: " + projectId));

	    member.setProject(project);  // 프로젝트와 멤버 연결
	    projectMemberRepository.save(member);  // 멤버 저장

	    return member;  // 추가된 멤버 반환
	}




	public List<ProjectMember> getMembersByProjectId(Long projectId) {
	    return projectMemberRepository.findByProject_Id(projectId);
	}



	
}
