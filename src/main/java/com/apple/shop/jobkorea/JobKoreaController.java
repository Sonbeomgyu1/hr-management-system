package com.apple.shop.jobkorea;

import com.apple.shop.comment.Comment;
import com.apple.shop.comment.CommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.apple.shop.member.CustomUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class JobKoreaController {

    private final JobKoreaRepository jobKoreaRepository;
    private  final CommentRepository commentRepository;

    //잡코리아리스트페이지로이동 및 데이터 들고오기
    @GetMapping("/jobkorea")
    String jobkorea(Model model) {
        List<JobKorea> result = jobKoreaRepository.findAll(Sort.by(Sort.Order.desc("lastModified")));

        model.addAttribute("jobkoreas", result);
        return "jobkorea/jobkorealist.html";
    }


    //잡코리아 글쓰기 페이지
    @GetMapping("/jobkoreawrite")
    String List(){

        return "jobkorea/jobkoreawrite.html";
    }
    //잡코리아 글쓰기 포스트맵핑
    @PostMapping("/jobkoreaadd")
    public String jobkoreaWrite(
            @RequestParam String jobkoreatypecd,
            @RequestParam String name,
            @RequestParam String birthday,
            @RequestParam LocalDate searchdate,
            @RequestParam String progressDesc,
            @RequestParam String area,
            @RequestParam String jobKoreaId,
            @RequestParam String outDate,
            @RequestParam String contactNum,
            @RequestParam String email,
            @RequestParam String skill,
            @RequestParam String carrer,
            @RequestParam String hopePrice,
            @RequestParam String projectNm,
            @RequestParam Integer manpowerId,
            Model model,
            Authentication auth) {

        CustomUser user = (CustomUser) auth.getPrincipal();
        // 받은 데이터를 객체에 저장하거나 DB에 저장하는 로직
        JobKorea jobKorea = new JobKorea();
         // 메서드 이름을 setDisplayName으로 수정
        jobKorea.setDisplayName(user.getDisplayName());
        jobKorea.setJobKoreaTypeCd(jobkoreatypecd);
        jobKorea.setName(name);
        jobKorea.setBirthday(birthday);
        jobKorea.setSearchDate(searchdate);
        jobKorea.setProgressDesc(progressDesc);
        jobKorea.setArea(area);
        jobKorea.setJobKoreaId(jobKoreaId);
        jobKorea.setOutDate(outDate);
        jobKorea.setContactNum(contactNum);
        jobKorea.setEmail(email);
        jobKorea.setSkill(skill);
        jobKorea.setCarrer(carrer);
        jobKorea.setHopePrice(hopePrice);
        jobKorea.setProjectNm(projectNm);
        jobKorea.setManpowerId(manpowerId);
        jobKoreaRepository.save(jobKorea);

        model.addAttribute("user",user);

        return "redirect:/jobkorea";  // 저장 후 페이지 이동
    }

    //아이디를 클릭했을떄 데이터들고 수정페이지로 넘어가는 코드
    @GetMapping("/jobkoreaedit/{id}")
    public String jobkoreaedit(@PathVariable Long id, Model model,
                               HttpServletRequest request,
                               Authentication auth){
        Optional<JobKorea> jobKorea = jobKoreaRepository.findById(id);
        if(jobKorea.isPresent()){
            model.addAttribute("jobkorea",jobKorea.get());
            //댓글목록
            List<Comment> comments = commentRepository.findAllByParentIdAndType(id,"jobkorea");
            // 댓글을 createdAt 기준으로 내림차순 정렬
            comments.sort((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()));
            model.addAttribute("comments", comments);
            //id 정보 가져오기
            CustomUser user = (CustomUser) auth.getPrincipal();
            model.addAttribute("currentUserDisplayName",user.getDisplayName());
            model.addAttribute("referer", request.getRequestURI());
        }else {
            return "redirect:/jobkorealist";
        }
        return "jobkorea/jobkoreaedit";
    }

    @PostMapping("/jobkoreaedit")
    public String jobKoreaEditSubmit(@ModelAttribute JobKorea jobKorea,
                                     @RequestParam(value = "jobKoreatypecd", required = false, defaultValue = "") String jobKoreatypecd ,
                                     @RequestParam LocalDate searchdate) {

        // 폼에서 전달된 값을 수동으로 설정
        jobKorea.setJobKoreaTypeCd(jobKoreatypecd);
        jobKorea.setSearchDate(searchdate);

        // 데이터 처리 로직 (저장, 수정 등)
        jobKoreaRepository.save(jobKorea);

        return "redirect:/jobkorea"; // 수정 후 목록으로 리디렉션
    }

    //삭제로직
    @PostMapping("/jobkoreadelete")
    public String jobKoreaDeleteSubmit(@RequestParam("id") Long id) {
        // 삭제 처리 로직
        jobKoreaRepository.deleteById(id);
        return "redirect:/jobkorea"; // 삭제 후 목록으로 리디렉션
    }


    //검색처리
    @PostMapping("/jobkorea/search")
    public String search(
            @RequestParam(value = "searchJobKoreaTypeCd", required = false) String searchJobKoreaTypeCd,
            @RequestParam(value = "searchDisplayName", required = false) String searchDisplayName,
            @RequestParam(value = "searchArea", required = false) String searchArea,
            @RequestParam(value = "searchName", required = false) String searchName,
            @RequestParam(value = "searchSkill", required = false) String searchSkill,
            Model model){
        List<JobKorea> jobKoreas = jobKoreaRepository.findBySearchFields( searchJobKoreaTypeCd, searchDisplayName,
                searchArea, searchName, searchSkill);

        model.addAttribute("jobkoreas", jobKoreas);
        return "jobkorea/jobkorealist"; // 검색 결과를 담은 페이지 반환
    }
}
