package com.apple.shop.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SalesRepository extends JpaRepository<Sales,Long> {

    // 특정 날짜에 해당하는 주문 내역을 가져오는 메소드
    List<Sales> findByCreatedBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);

    @Query("SELECT s FROM Sales s JOIN FETCH s.member")  //jpql문법
    List<Sales> findAllWithMember();

    // 로그인한 사용자의 ID에 해당하는 주문 내역만 가져오는 메소드 추가
    @Query("SELECT s FROM Sales s WHERE s.member.id = :memberId")
    List<Sales> findByMemberId(Long memberId);


}
