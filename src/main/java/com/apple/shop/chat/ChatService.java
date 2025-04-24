package com.apple.shop.chat;

import com.apple.shop.chat.ChatMessageDTO;
import com.apple.shop.chat.ChatMessage;
import com.apple.shop.chat.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage saveMessage(ChatMessageDTO dto) {
        ChatMessage message = ChatMessage.builder()
                .roomId(dto.getRoomId())
                .sender(dto.getSender())
                .content(dto.getContent())
                .sendTime(LocalDateTime.now())
                .build();

        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessages(String roomId) {
        // 예외 처리를 추가하여 오류를 로깅합니다.
        try {
            return chatMessageRepository.findByRoomIdOrderBySendTimeAsc(roomId);
        } catch (Exception e) {
            // 예외 발생 시 로그를 기록하고 빈 리스트 반환
            e.printStackTrace();
            return List.of();
        }
    }
    
    public List<String> getAllDistinctRoomIds() {
        return chatMessageRepository.findDistinctRoomIds();  // 중복 없는 방 목록 반환
    }

}