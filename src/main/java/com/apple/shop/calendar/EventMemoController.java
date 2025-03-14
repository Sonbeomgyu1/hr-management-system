package com.apple.shop.calendar;


import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import com.apple.shop.member.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/memos")
public class EventMemoController {

    private final EventMemoRepository eventMemoRepository;
    private final MemberRepository memberRepository;

    public EventMemoController(EventMemoRepository eventMemoRepository, MemberRepository memberRepository) {
        this.eventMemoRepository = eventMemoRepository;
        this.memberRepository = memberRepository;
    }

    // 메모 목록 가져오기
    @GetMapping("/all")
    public List<EventMemo> getAllMemos() {
        return eventMemoRepository.findAll();
    }

    // 메모 저장
    @PostMapping("/save")
    public String saveMemo(@RequestBody MemoRequest memoRequest, Authentication authentication) {
        // 로그인한 사용자 정보 가져오기
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        String username = customUser.getUsername();
        String displayName = customUser.getDisplayName();

        // 요청에서 전달된 날짜, 메모 내용
        String date = memoRequest.getDate();
        String memoText = memoRequest.getMemo();

        // 사용자 정보 찾기
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // EventMemo 객체 생성
        EventMemo eventMemo = new EventMemo();
        eventMemo.setMember(member);
        eventMemo.setDisplayName(displayName);
        eventMemo.setDate(LocalDate.parse(date));  // 날짜 설정
        eventMemo.setMemo(memoText);  // 메모 내용 설정

        // 메모 저장
        eventMemoRepository.save(eventMemo);
        return "메모가 저장되었습니다.";
    }

    // 메모 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteMemo(@PathVariable Long id) {
        EventMemo eventMemo = eventMemoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 메모를 찾을 수 없습니다."));

        eventMemoRepository.delete(eventMemo);
        return "메모가 삭제되었습니다.";
    }

    // 메모 수정
    @PutMapping("/update/{id}")
    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequest memoRequest, Authentication authentication) {
        // 로그인한 사용자 정보 가져오기
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        String username = customUser.getUsername();
        String displayName = customUser.getDisplayName();

        // 요청에서 전달된 날짜, 메모 내용
        String date = memoRequest.getDate();
        String memoText = memoRequest.getMemo();


        // 기존 메모 찾기
        EventMemo eventMemo = eventMemoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정할 메모를 찾을 수 없습니다."));

        // 사용자 정보가 일치하는지 확인
        if (!eventMemo.getMember().getUsername().equals(username)) {
            throw new IllegalArgumentException("해당 메모를 수정할 권한이 없습니다.");
        }

        // 메모 내용 수정
        eventMemo.setDisplayName(displayName);
        eventMemo.setDate(LocalDate.parse(date));  // 날짜 수정
        eventMemo.setMemo(memoText);  // 메모 내용 수정

        // 메모 저장 (업데이트)
        eventMemoRepository.save(eventMemo);
        return "메모가 수정되었습니다.";
    }
}
