package com.apple.shop.calendar;

import com.apple.shop.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface EventMemoRepository extends JpaRepository<EventMemo, Long> {
    List<EventMemo> findByMemberAndDate(Member member, LocalDate date);
}
