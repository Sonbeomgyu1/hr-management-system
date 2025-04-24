package com.apple.shop.CustomerMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMemberRepository extends JpaRepository<CustomerMember, Long> {
    // 이름으로 검색하는 기능 추가
    List<CustomerMember> findByNameContaining(String name);

    List<CustomerMember> findAllByUserId(Long userId); //로그인한 사용자의 ID(userId)에 해당하는 데이터만 가져올 수 있습니다


    @Query(value = "select * from shop.customer_member where name like %?1%", nativeQuery = true)
    List<CustomerMember> rawQuery2(String text);




}
