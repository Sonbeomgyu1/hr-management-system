package com.apple.shop.CustomerMember;

import com.apple.shop.comment.Comment;
import com.apple.shop.comment.CommentRepository;
import com.apple.shop.member.CustomUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerMemberRepository customerMemberRepository;
    private final CommentRepository commentRepository;



    @GetMapping("/customerlist")
    public String customerList(Model model, Authentication authentication) {
        CustomUser user = (CustomUser) authentication.getPrincipal();  // 🔹 로그인한 사용자 가져오기
        Long userId = user.getId();

        List<CustomerMember> customers = customerMemberRepository.findAllByUserId(userId);  // 🔹 userId로 필터링
        model.addAttribute("customers", customers);
        return "/customermember/customerlist";
    }


    @GetMapping("/customerwrite")
    public String customerwrite(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String region,
                                @RequestParam(required = false) String phoneNumber,
                                @RequestParam(required = false) String skill,
                                @RequestParam(required = false) String grade,
                                Model model) {
//        System.out.println("===== 요청 받은 데이터 =====");
//        System.out.println("name: " + name);
//        System.out.println("region: " + region);
//        System.out.println("phoneNumber: " + phoneNumber);
//        System.out.println("skill: " + skill);
//        System.out.println("grade: " + grade);

        // CustomerMember 객체 생성 후 값 설정
        CustomerMember customerMember = new CustomerMember();
        customerMember.setName(name);
        customerMember.setRegion(region);
        customerMember.setPhoneNumber(phoneNumber);
        customerMember.setSkill(skill);
        customerMember.setGrade(grade);

        // Model에 customerMember 객체 추가
        model.addAttribute("customer", customerMember); // 수정된 부분

        return "customermember/customerwrite";
    }


    @PostMapping("/customerAdd")
    String customerAdd(@RequestParam String name,
                       @RequestParam String region,
                       @RequestParam String phoneNumber,
                       @RequestParam String skill,
                       @RequestParam String grade,
                       @RequestParam String content,
                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdDate,
                       Authentication authentication) {  // 🔹 로그인 정보 추가
        CustomUser user = (CustomUser) authentication.getPrincipal();  // 🔹 현재 로그인한 사용자 가져오기
        Long userId = user.getId();  // 🔹 userId 가져오기

        System.out.println("저장되는 userId: " + userId);  // << 로그 확인

        CustomerMember customerMember = new CustomerMember();
        customerMember.setName(name);
        customerMember.setRegion(region);
        customerMember.setPhoneNumber(phoneNumber);
        customerMember.setSkill(skill);
        customerMember.setGrade(grade);
        customerMember.setContent(content);
        customerMember.setCreatedDate(createdDate);
        customerMember.setUserId(userId);  // 🔹 userId 저장

        customerMemberRepository.save(customerMember);
        return "redirect:/customerlist";
    }


    @GetMapping("/customerdetail/{id}")
    public String customerdetail(@PathVariable Long id, Model model, HttpServletRequest request) {
        Optional<CustomerMember> result = customerMemberRepository.findById(id);

        if (result.isPresent()) {
            CustomerMember customer = result.get();
            model.addAttribute("customer", customer);

            // 해당 고객에 대한 댓글 목록 조회
            List<Comment> comments = commentRepository.findAllByParentIdAndType(id, "customer");
            model.addAttribute("comments", comments);  // 댓글 데이터를 모델에 추가

            model.addAttribute("referer", request.getRequestURI());  // referer URL 추가

            return "customermember/customerdetail"; // 올바른 Thymeleaf 템플릿 반환
        } else {
            return "redirect:/customerlist"; // 고객이 없을 경우 다른 페이지로 리디렉션
        }
    }


    @GetMapping("/customeredit/{id}")
    public String editBoard(@PathVariable Long id, Model model){
        Optional<CustomerMember> customerMember = customerMemberRepository.findById(id);
        if(customerMember.isPresent()){
            model.addAttribute("customer",customerMember.get());
        } else {
            return "redirect:/customerlist";
        }
        return "customermember/customeredit";
    }

    @PostMapping("/customeredit")
    public String customeredit(@RequestParam Long id,  // 수정할 고객의 ID를 받음
                               @RequestParam String name,
                               @RequestParam String region,
                               @RequestParam String phoneNumber,
                               @RequestParam String skill,
                               @RequestParam String grade,
                               @RequestParam String content,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdDate,
                               Authentication authentication) {

        // 로그인한 사용자 정보 가져오기
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Long userId = customUser.getId();  // 사용자 ID 가져오기

        // 기존 데이터를 조회합니다.
        Optional<CustomerMember> existingCustomerMember = customerMemberRepository.findById(id);

        if (existingCustomerMember.isPresent()) {
            CustomerMember customerMember = existingCustomerMember.get();

            // 기존 데이터를 수정합니다.
            customerMember.setName(name);
            customerMember.setRegion(region);
            customerMember.setPhoneNumber(phoneNumber);
            customerMember.setSkill(skill);
            customerMember.setGrade(grade);
            customerMember.setContent(content);
            customerMember.setCreatedDate(createdDate);
            customerMember.setUserId(userId);  // user_id 설정

            // 수정된 고객 데이터를 저장합니다.
            customerMemberRepository.save(customerMember);
        } else {
            // 만약 수정할 고객을 찾을 수 없다면, 리다이렉트 처리
            return "redirect:/customerlist";
        }

        return "redirect:/customerlist";
    }


    @DeleteMapping("/customerlist")
    ResponseEntity<String> deleteCustomer(@RequestParam long id){
        customerMemberRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @PostMapping("/customersearch")
    public String CustomerSearch(@RequestParam(required = false) String searchText, Model model) {
        System.out.println("검색할 텍스트: " + searchText);  // 검색 텍스트 출력
        var result = customerMemberRepository.rawQuery2(searchText);
        System.out.println("검색 결과: " + result);

        if (result == null || result.isEmpty()) {
            model.addAttribute("customers", new ArrayList<>()); // 빈 리스트 전달
        } else {
            model.addAttribute("customers", result);
        }

        return "customermember/customerlist"; // 결과 페이지로 리다이렉트
    }




}