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
    public String customerwrite(@RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "region", required = false) String region,
                                @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                @RequestParam(name = "skill", required = false) String skill,
                                @RequestParam(name = "grade", required = false) String grade,
                                Model model) {

        CustomerMember customerMember = new CustomerMember();
        customerMember.setName(name);
        customerMember.setRegion(region);
        customerMember.setPhoneNumber(phoneNumber);
        customerMember.setSkill(skill);
        customerMember.setGrade(grade);

        model.addAttribute("customer", customerMember);

        return "customermember/customerwrite";
    }

    @PostMapping("/customerAdd")
    String customerAdd(@RequestParam(name = "name") String name,
                       @RequestParam(name = "region") String region,
                       @RequestParam(name = "phoneNumber") String phoneNumber,
                       @RequestParam(name = "skill") String skill,
                       @RequestParam(name = "grade") String grade,
                       @RequestParam(name = "content") String content,
                       @RequestParam(name = "createdDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdDate,
                       Authentication authentication) {
        CustomUser user = (CustomUser) authentication.getPrincipal();  
        Long userId = user.getId();  

        CustomerMember customerMember = new CustomerMember();
        customerMember.setName(name);
        customerMember.setRegion(region);
        customerMember.setPhoneNumber(phoneNumber);
        customerMember.setSkill(skill);
        customerMember.setGrade(grade);
        customerMember.setContent(content);
        customerMember.setCreatedDate(createdDate);
        customerMember.setUserId(userId);  

        customerMemberRepository.save(customerMember);
        return "redirect:/customerlist";
    }

    @GetMapping("/customerdetail/{id}")
    public String customerdetail(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request) {
        Optional<CustomerMember> result = customerMemberRepository.findById(id);

        if (result.isPresent()) {
            CustomerMember customer = result.get();
            model.addAttribute("customer", customer);

            List<Comment> comments = commentRepository.findAllByParentIdAndType(id, "customer");
            model.addAttribute("comments", comments);  

            model.addAttribute("referer", request.getRequestURI());  

            return "customermember/customerdetail"; 
        } else {
            return "redirect:/customerlist"; 
        }
    }

    @GetMapping("/customeredit/{id}")
    public String editBoard(@PathVariable(name = "id") Long id, Model model) {
        Optional<CustomerMember> customerMember = customerMemberRepository.findById(id);
        if (customerMember.isPresent()) {
            model.addAttribute("customer", customerMember.get());
        } else {
            return "redirect:/customerlist";
        }
        return "customermember/customeredit";
    }

    @PostMapping("/customeredit")
    public String customeredit(@RequestParam(name = "id") Long id,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "region") String region,
                               @RequestParam(name = "phoneNumber") String phoneNumber,
                               @RequestParam(name = "skill") String skill,
                               @RequestParam(name = "grade") String grade,
                               @RequestParam(name = "content") String content,
                               @RequestParam(name = "createdDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdDate,
                               Authentication authentication) {

        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Long userId = customUser.getId(); 

        Optional<CustomerMember> existingCustomerMember = customerMemberRepository.findById(id);

        if (existingCustomerMember.isPresent()) {
            CustomerMember customerMember = existingCustomerMember.get();

            customerMember.setName(name);
            customerMember.setRegion(region);
            customerMember.setPhoneNumber(phoneNumber);
            customerMember.setSkill(skill);
            customerMember.setGrade(grade);
            customerMember.setContent(content);
            customerMember.setCreatedDate(createdDate);
            customerMember.setUserId(userId); 

            customerMemberRepository.save(customerMember);
        } else {
            return "redirect:/customerlist";
        }

        return "redirect:/customerlist";
    }

    @DeleteMapping("/customerlist")
    ResponseEntity<String> deleteCustomer(@RequestParam(name = "id") long id) {
        customerMemberRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @PostMapping("/customersearch")
    public String CustomerSearch(@RequestParam(name = "searchText", required = false) String searchText, Model model) {
        var result = customerMemberRepository.rawQuery2(searchText);

        if (result == null || result.isEmpty()) {
            model.addAttribute("customers", new ArrayList<>());
        } else {
            model.addAttribute("customers", result);
        }

        return "customermember/customerlist"; 
    }
    
    @GetMapping("/api/customerlist")
    @ResponseBody
    public List<CustomerMember> getCustomerListJson(Authentication authentication) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        Long userId = user.getId();
        return customerMemberRepository.findAllByUserId(userId);
    }

}
