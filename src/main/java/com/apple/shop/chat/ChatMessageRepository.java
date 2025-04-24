package com.apple.shop.chat;

import com.apple.shop.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomIdOrderBySendTimeAsc(String roomId);
    // roomId로 메시지를 조회
    List<ChatMessage> findByRoomId(String roomId);
    
    // 방 목록을 중복 없이 가져오는 쿼리
    @Query("SELECT DISTINCT cm.roomId FROM ChatMessage cm")
    List<String> findDistinctRoomIds();
}