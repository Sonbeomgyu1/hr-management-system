package com.apple.shop.Attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.apple.shop.member.Member;
import com.apple.shop.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;

    // 출퇴근 기록 목록 조회
    @GetMapping("/list")
    public String getAttendanceList(Model model) {
        // 로그인한 사용자 정보 가져오기 (Spring Security 사용 시)
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername(); // 로그인한 사용자의 ID

        // 관리자 권한 확인
        boolean isAdmin = principal.getAuthorities().stream()
                                   .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        List<Attendance> attendances;

        if (isAdmin) {
            // 관리자일 경우 모든 출퇴근 기록을 조회
            attendances = attendanceRepository.findAll();
        } else {
            // 일반 사용자일 경우 본인 출퇴근 기록만 조회
            Member member = memberRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid member username"));
            attendances = attendanceRepository.findByMemberId(member.getId()); // 해당 멤버의 출퇴근 기록만 조회
        }

        // 날짜별로 출퇴근 기록을 그룹화
        Map<LocalDate, List<Attendance>> groupedByDate = attendances.stream()
                .collect(Collectors.groupingBy(
                        attendance -> attendance.getCheckIn().toLocalDate(),
                        () -> new TreeMap<LocalDate, List<Attendance>>(Comparator.reverseOrder()),
                        Collectors.toList()
                ));


        model.addAttribute("groupedAttendances", groupedByDate);
        return "attendance/list"; // 출퇴근 기록 목록 페이지
    }



    @GetMapping("/attendance/list")
    public String showAttendanceList(Model model) {
        // 현재 로그인된 사용자 정보 얻기
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        // DB에서 Member 조회
        Member member = memberRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        // 해당 멤버의 출퇴근 기록 조회
        List<Attendance> attendances = attendanceRepository.findByMemberId(member.getId());

        // 모델에 데이터 담기
        model.addAttribute("attendances", attendances);
        model.addAttribute("memberId", member.getId()); // ✅ 이거 꼭 있어야 함!

        return "attendance/list";
    }





    // 출퇴근 기록을 추가하는 메서드
    @PostMapping("/addAttendance")
    public String addAttendance(@RequestParam(name = "type") AttendanceType type,
                                @RequestParam(name = "checkOut", required = false) LocalDateTime checkOut) {

        // 현재 로그인한 사용자 정보 가져오기
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member username"));

        Attendance attendance = new Attendance();
        attendance.setMember(member);
        attendance.setType(type);
        attendance.setCheckIn(LocalDateTime.now());
        attendance.setCheckOut(checkOut);

        attendanceRepository.save(attendance);
        return "redirect:/attendance/list";
    }




    // 특정 멤버의 출퇴근 기록을 조회하는 메서드
    @GetMapping("/member/{memberId}")
    public String getAttendanceByMember(@PathVariable("memberId") Long memberId, Model model) {
        List<Attendance> attendances = attendanceRepository.findByMemberId(memberId);

        // 로그인한 사용자 정보 가져오기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        model.addAttribute("attendances", attendances);
        model.addAttribute("member", member);  // 로그인한 사용자의 정보도 전달
        return "attendance/memberAttendance"; // 특정 멤버의 출퇴근 기록 페이지
    }
    
 // 퇴근 시간 기록 메서드
    @PostMapping("/setCheckOut/{attendanceId}")
    public String setCheckOutTime(@PathVariable("attendanceId") Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid attendance ID"));

        // 퇴근 시간 설정
        if (attendance.getCheckOut() == null) {
            attendance.setCheckOut(LocalDateTime.now());
            attendanceRepository.save(attendance);
        }

        return "redirect:/attendance/list";  // 출퇴근 기록 목록 페이지로 리디렉션
    }


    @PostMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (!attendanceRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("message", "삭제 실패: 해당 ID가 존재하지 않음");
        } else {
            attendanceRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "ID " + id + " 삭제 완료!");
        }
        return "redirect:/attendance/list"; // 출석 목록 페이지로 리디렉트
    }




  

    
    
    
}
