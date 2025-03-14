package com.apple.shop.manpower;

import com.apple.shop.comment.Comment;
import com.apple.shop.comment.CommentRepository;
import com.apple.shop.member.CustomUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ManPowerController {

    private final ManPowerRepository manPowerRepository;
    private final CommentRepository commentRepository;

    //프리랜서 리스트 페이지
    @GetMapping("/manpower")
    public String manpower (Model model){
        List<ManPower> result = manPowerRepository.findAll(Sort.by(Sort.Order.desc("id")));
        model.addAttribute("manPower",result);
        return "manpower/manpowerlist.html";
    }


    @GetMapping("/manpowerwrite")
    public String manpowerwrite(
            @RequestParam(required = false) String displayName,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String birthday,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String contactNumber,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String workArea,
            @RequestParam(required = false) String jobkoreaId,
            @RequestParam(required = false) Long id,
            Model model) {

        ManPower manPower = new ManPower();

        // 기존의 데이터가 아닌 새로운 데이터 입력이라면, 다음 ID를 계산
        if (id == null) {
            Long nextId = manPowerRepository.getNextId();  // 데이터베이스에서 다음 ID 계산 (기능 구현 필요)
            model.addAttribute("nextId", nextId);  // 다음 ID를 모델에 추가
        } else {
            // 데이터베이스에서 해당 id로 ManPower 객체 조회
            manPower = manPowerRepository.findById(id).orElse(new ManPower());
        }
//        System.out.println("===== 요청 받은 데이터 =====");
//        System.out.println("displayName: " + displayName);
//        System.out.println("name: " + name);
//        System.out.println("birthday: " + birthday);
//        System.out.println("contactNumber: " + contactNumber);
//        System.out.println("email: " + email);
//        System.out.println("workArea: " + workArea);
//        System.out.println("skill :" + skills);
//        System.out.println("jobkoreaId :" + jobkoreaId);
        // 모델에 전달할 데이터
        model.addAttribute("manPower", manPower); // 모델에 객체 추가
        model.addAttribute("displayName", displayName);
        model.addAttribute("name", name);
        model.addAttribute("birthday", birthday);
        model.addAttribute("email", email);
        model.addAttribute("contactNumber", contactNumber);
        model.addAttribute("skills", skills);
        model.addAttribute("workArea", workArea);
        model.addAttribute("jobkoreaId",jobkoreaId);

        return "manpower/manpowerwrite.html"; // 글쓰기 페이지 반환
    }



    // ManPower 데이터 추가
    @PostMapping("/manpoweradd")
    public String manpoweradd(
            @RequestParam String displayName,
            @RequestParam String name,
            @RequestParam(required = false) String jobPosition,
            @RequestParam(required = false) String birthday,
            @RequestParam(required = false) String contactNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String searchDate,
            @RequestParam(required = false) String workArea,
            @RequestParam(required = false) String careerYears,
            @RequestParam(required = false) String educationLevel,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) String proposal,
            @RequestParam(required = false) String jobkoreaId,
            @RequestParam(required = false) String certifications,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String additionalNote,
            @RequestParam(required = false) String evaluation,
            @RequestParam(required = false) String projects,
            Model model, Authentication auth) {


        try {
            CustomUser user = (CustomUser) auth.getPrincipal();

            // 새로운 ManPower 객체 생성
            ManPower manPower = new ManPower();
            manPower.setDisplayName(displayName);
            manPower.setName(name);
            manPower.setJobPosition(jobPosition);
            manPower.setBirthday(birthday);
            manPower.setContactNumber(contactNumber);
            manPower.setEmail(email);
            manPower.setSearchDate(LocalDate.parse(searchDate)); // 날짜 형식으로 변환
            manPower.setWorkArea(workArea);
            manPower.setCareerYears(careerYears);
            manPower.setEducationLevel(educationLevel);
            manPower.setRemark(remark);
            manPower.setProposal(proposal);
            manPower.setJobkoreaId(jobkoreaId);
            manPower.setCertifications(certifications);
            manPower.setSkills(skills);
            manPower.setAdditionalNote(additionalNote);
            manPower.setEvaluation(evaluation);
            manPower.setProjects(projects);

            // ManPower 데이터를 데이터베이스에 저장
            manPowerRepository.save(manPower);

            model.addAttribute("message", "ManPower가 성공적으로 등록되었습니다.");

            return "redirect:/manpower"; // 등록 후 목록 페이지로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("message", "등록 중 오류가 발생하였습니다.");
            return "/manpower/manpowerwrite.html"; // 오류 발생 시 등록 폼으로 돌아가기
        }
    }

    @GetMapping("/manpoweredit/{id}")
    public String manpoweredit(@PathVariable Long id, Model model,
                               HttpServletRequest request,
                               Authentication auth) {
        Optional<ManPower> manPower = manPowerRepository.findById(id);
        if(manPower.isPresent()){
            model.addAttribute("manPower",manPower.get());
        //댓글 목록
            List<Comment> comments = commentRepository.findAllByParentIdAndType(id,"manpower");
            // 댓글을 createdAt 기준으로 내림차순 정렬
            comments.sort((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()));
            model.addAttribute("comments",comments); //댓글데이터를 목록에추가
        //유저 정보 추가
            CustomUser user = (CustomUser) auth.getPrincipal();
            model.addAttribute(user.getDisplayName());
            //저장
            model.addAttribute("referer", request.getRequestURI());
            return "manpower/manpoweredit";
        }else{
            return "redirect:/manpower";
        }

    }

    @PostMapping("/manpoweredit")
    public String manpoweredit(@ModelAttribute ManPower manPower,
                                @RequestParam(value = "jobPosition", required = false, defaultValue = "") String jobPosition ,
                               @RequestParam LocalDate searchDate
                               ) {
        manPower.setJobPosition(jobPosition);
        manPower.setSearchDate(searchDate);

        manPowerRepository.save(manPower);
        return "redirect:/manpower";
    }

    @PostMapping("/manpowerdelete")
    public String manpowerdelete(@RequestParam("id") Long id){
        manPowerRepository.deleteById(id);
        return "redirect:/manpower";
    }

    @PostMapping("/manpower/search")
    public String searchManpower(
            @RequestParam(required = false) String searchDisplayName,
            @RequestParam(required = false) String searchArea,
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) String searchSkills,
            @RequestParam(required = false) String searchremark,
            @RequestParam(required = false) String searchcareerYears,
            Model model) {

        // 여기에 검색 로직을 추가하여 manPower 객체들을 찾습니다.
        List<ManPower> result = manPowerRepository.findBySearchFields(searchDisplayName, searchArea, searchName, searchSkills, searchremark, searchcareerYears);

        model.addAttribute("manPower", result);

        // 검색을 한번 더 눌렀을 때 전체 데이터를 보여주기 위한 리다이렉트
        if (searchDisplayName == null && searchArea == null && searchName == null
                && searchSkills == null && searchremark == null && searchcareerYears == null) {
            return "redirect:/manpower";  // 전체 데이터를 다시 불러옴
        }

        return "manpower/manpowerlist"; // 검색 결과를 보여줄 페이지
    }



}


