package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;


    @PostMapping("/order")
    String postOrder(@RequestParam String title,
                     @RequestParam Integer price,
                     @RequestParam Integer count,
                     Authentication auth){
        Sales sales = new Sales();
        sales.setCount(count);
        sales.setPrice(price);
        sales.setItemName(title);
        CustomUser user = (CustomUser) auth.getPrincipal(); //유저의 정보 가져오기
        Member member = new Member(); //유저의 정보 가져오기
        member.setId(user.id); //유저의 정보 가져오기
        sales.setMember(member); //유저의 정보 가져오기
        salesRepository.save(sales);
        return "item/list.html";
    }

    //Sales가 가진 엔티티값 전부 출력
    // 로그인한 사용자의 주문 내역만 가져오기
//    @GetMapping("/order/all")
//    public String getOrderAll(Model model, Authentication auth) {
//        // 로그인한 사용자의 ID 가져오기
//        CustomUser user = (CustomUser) auth.getPrincipal();  // 로그인한 사용자 정보 가져오기
//        Long memberId = user.getId();  // 로그인한 사용자의 ID
//
//        // 로그인한 사용자에 해당하는 주문 내역만 가져오기
//        List<Sales> result = salesRepository.findByMemberId(memberId);
//        model.addAttribute("orders", result);  // 주문 내역을 Thymeleaf에 전달
//
//        return "sales/order_list.html";  // mypage.html로 리턴
//    }

    // 전체 주문목록
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
// 해당 메소드를 실행하기 전에 사용자가 'ROLE_ADMIN' 권한을 가지고 있는지 확인.
// ROLE_ADMIN 권한이 있는 사용자만 접근할 수 있도록 제한함.
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/order/all")
    public String getOrderAll(@RequestParam(required = false) LocalDate startDate,  // 시작 날짜 (URL 파라미터로 입력)
                              @RequestParam(required = false) LocalDate endDate,    // 종료 날짜 (URL 파라미터로 입력)
                              Model model) {  // Thymeleaf 모델을 사용하여 뷰에 데이터를 전달
        List<Sales> result;  // Sales 데이터를 저장할 변수

        // 만약 시작 날짜와 종료 날짜가 모두 제공되면,
        if (startDate != null && endDate != null) {
            // 날짜 범위에 해당하는 주문 내역을 조회
            // startDate.atStartOfDay() -> 시작 날짜의 00:00:00으로 변환
            // endDate.plusDays(1).atStartOfDay() -> 종료 날짜의 23:59:59를 포함하기 위해 종료 날짜에 1일을 더해 00:00:00으로 변환
            result = salesRepository.findByCreatedBetween(
                    startDate.atStartOfDay(),  // 시작일 00:00:00
                    endDate.plusDays(1).atStartOfDay()  // 종료일 23:59:59 포함
            );
        } else {
            // 시작일과 종료일이 제공되지 않으면, 전체 주문 내역을 조회
            result = salesRepository.findAll();
        }

        // 날짜별로 주문 내역을 그룹화 (Map<LocalDate, List<Sales>>)
        // .stream() -> result 리스트를 스트림으로 변환하여 처리
        // .collect(Collectors.groupingBy(...)) -> 스트림을 그룹화하여 날짜별로 주문을 묶어 Map 형태로 저장
        // order.getCreated().toLocalDate() -> Sales의 주문 시간(created)을 LocalDate로 변환하여 날짜 기준으로 그룹화
        Map<LocalDate, List<Sales>> groupedByDate = result.stream()
                .collect(Collectors.groupingBy(order -> order.getCreated().toLocalDate()));

        // 모델에 'ordersByDate'라는 이름으로 날짜별 그룹화된 주문 데이터를 추가
        // 이를 통해 Thymeleaf 템플릿에서 날짜별로 주문 내역을 처리할 수 있음
        model.addAttribute("ordersByDate", groupedByDate);

        // Thymeleaf 템플릿으로 데이터를 전달하며, 해당 템플릿에서 'ordersByDate'를 사용해 결과를 출력
        return "sales/order_list.html";
    }





}
