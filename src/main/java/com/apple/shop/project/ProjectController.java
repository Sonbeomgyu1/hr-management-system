package com.apple.shop.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.apple.shop.CustomerMember.CustomerMember;
import com.apple.shop.CustomerMember.CustomerMemberRepository;

import java.util.List;
import java.util.Optional;

@Controller  
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private CustomerMemberRepository customerMemberRepository;

    // 프로젝트 생성
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }

    // 프로젝트 등록 화면
    @GetMapping("/write")
    public String showProjectWrite(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects/projectswrite"; // templates/projects/projectswrite.html
    }

    // 프로젝트 목록 조회
    @GetMapping("/list")
    public String showProjectList(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects/projectslist"; // templates/projects/projectslist.html
    }

    // 프로젝트 목록 조회 (RESTful API)
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

  

    

    @PostMapping("/{projectId}")
    public String updateProject(@PathVariable("projectId") Long projectId, @ModelAttribute Project updatedProject) {
        boolean isUpdated = projectService.updateProject(projectId, updatedProject);
        if (isUpdated) {
            return "redirect:/projects/" + projectId;
        }
        return "redirect:/projects/projectslist";
    }



 // 수정 폼 보여주기
    @GetMapping("/{projectId}/edit")
    public String editProject(@PathVariable("projectId") Long projectId, Model model) {
        Optional<Project> project = projectService.getProjectById(projectId);
        if (project.isPresent()) {
            model.addAttribute("project", project.get());
            return "projects/projectedit"; // 수정 페이지 템플릿
        }
        return "redirect:/projects/projectslist";
    }


    // 프로젝트 삭제
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

 // 프로젝트에 멤버 추가
    @PostMapping("/{projectId}/members")
    public ResponseEntity<List<ProjectMember>> addMemberToProject(
        @PathVariable("projectId") Long projectId, 
        @RequestBody ProjectMember member
    ) {
        // customerMemberId가 주어졌다면, 이를 통해 CustomerMember 객체를 찾기
        if (member.getCustomerMember() == null && member.getCustomerMemberId() != null) {
            CustomerMember cm = customerMemberRepository.findById(member.getCustomerMemberId())
                                                        .orElse(null);  // 없으면 null 처리
            if (cm != null) {
                member.setCustomerMember(cm);  // customerMember 세팅
            } else {
                return ResponseEntity.badRequest().body(null);  // 찾을 수 없으면 에러 응답
            }
        }

        // 멤버 추가
        ProjectMember addedMember = projectService.addMemberToProject(projectId, member);

        // 프로젝트 멤버 목록 가져오기
        List<ProjectMember> updatedMembers = projectService.getMembersByProjectId(projectId);

        return ResponseEntity.ok(updatedMembers);
    }





    @GetMapping("/{projectId}")
    public String getProjectDetails1(@PathVariable("projectId") Long projectId, Model model) {
        Optional<Project> project = projectService.getProjectById(projectId);
        if (project.isPresent()) {
            model.addAttribute("project", project.get());

            // ✅ 멤버 목록도 모델에 담기
            List<ProjectMember> members = projectService.getMembersByProjectId(projectId);
            model.addAttribute("members", members);
            System.out.println("멤버 수: " + members.size());

            return "projects/projectdetail";
        }
        return "redirect:/projects/list";
    }
    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<ProjectMember>> getMembersByProjectId(@PathVariable Long projectId) {
        List<ProjectMember> members = projectService.getMembersByProjectId(projectId);
        return ResponseEntity.ok(members);
    }

    
    
}
