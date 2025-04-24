package com.apple.shop.member;

import com.apple.shop.contract.Contract;
import com.apple.shop.contract.ContractRepository;
import com.apple.shop.sales.Sales;
import com.apple.shop.sales.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; //시큐리티컨피그에 있음
    private final SalesRepository salesRepository;
    private final ContractRepository contractRepository;

    //회원가입 form 겟매핑
    @GetMapping("/register")
    public String register() {
        return "/member/register.html";
    }

    //회원가입 폼에서 데이터베이스로 데이터보내기 (회원가입)
    @PostMapping("/member")
    public String addMember(@RequestParam("displayName") String displayName, @RequestParam("username") String username, @RequestParam("password") String password) {
        Member member = new Member();
        member.setDisplayName(displayName);
        member.setUsername(username);
        
        // 비밀번호 암호화
        String hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        
        // Member 객체 저장
        memberRepository.save(member);
        
        return "redirect:/";  // 회원 목록 페이지로 리다이렉트
    }


    //로그인폼 겟매핑!!
    @GetMapping("/login")
    public String login() {
        return "member/login.html";
    }

    //Authentication auth 매개변수를 사용하면 Spring Security가 현재 로그인한 사용자의 인증 정보를 자동으로 주입
    //로그인한 유저만 볼수있는 페이지
    @GetMapping("/my-page")
    public String myPage(Authentication auth, Model model) {
        CustomUser result = (CustomUser) auth.getPrincipal();
        Long memberId = result.getId();
        String role = auth.getAuthorities().iterator().next().getAuthority(); // "ROLE_ADMIN" or "ROLE_USER"

        // 주문 내역 가져오기
        List<Sales> sales = salesRepository.findByMemberId(memberId);

        // 날짜별 그룹화
        Map<String, List<Sales>> groupedOrders = sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getCreated().toLocalDate().toString()));

        // 계약서 가져오기 (관리자: 전체, 유저: 본인 것만)
        List<Contract> contracts;
        if ("ROLE_ADMIN".equals(role)) {
            contracts = contractRepository.findAll();
        } else {
            contracts = contractRepository.findByMemberId(memberId);
        }

        model.addAttribute("groupedOrders", groupedOrders);
        model.addAttribute("contracts", contracts);
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("role", role);

        return "member/mypage.html";
    }




    @GetMapping("/user/1")
    @ResponseBody
    public MemberDto getUser() {
       var a = memberRepository.findById(1L);
       var result = a.get();
       var data = new MemberDto(result.getUsername(),result.getDisplayName(),result.getId());
        return data;
    }

    //데이터를 다른곳으로 보낼떄 Dto클래스 만들기
    class MemberDto{
        public String username;
        public String displayName;
        public Long Id;
        //파라미터를 2개 쓰고 싶을떄
        MemberDto(String a , String b){
            this.username = a;
            this.displayName = b;
        }
        //파라미터를 3개 쓰고싶을떄
        MemberDto(String a, String b, Long Id){
            this.username = a;
            this.displayName = b;
            this.Id = Id;
        }
    }

}
