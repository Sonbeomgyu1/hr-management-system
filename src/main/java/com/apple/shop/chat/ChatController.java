package com.apple.shop.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;
import com.apple.shop.member.CustomUser;

import java.util.List;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    // 채팅 페이지 요청 처리
    @GetMapping("/chat")
    public String chatPage() {
        return "/chat/chat"; // chat.html 파일을 반환
    }

    // 채팅방에 존재하는 메시지 목록을 클라이언트에 전달하는 API 추가
    @GetMapping("/api/chat/messages")
    public ResponseEntity<List<ChatMessage>> getMessages(@RequestParam("roomId") String roomId) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Room ID is required.");
        }
        List<ChatMessage> messages = chatService.getMessages(roomId);
        return ResponseEntity.ok(messages); // JSON 형식으로 반환
    }



    // 클라이언트에서 /app/chat 요청을 보내면 이 메서드가 호출됨
    @MessageMapping("/chat") // /pub/chat
    public void handleChatMessage(ChatMessageDTO message, Authentication authentication) {
        // 로그인한 사용자 정보 추출
        CustomUser user = (CustomUser) authentication.getPrincipal();
        message.setSender(user.getDisplayName());

        // 메시지 저장
        ChatMessage saved = chatService.saveMessage(message);

        // 클라이언트로 메시지 전송
        messagingTemplate.convertAndSend("/topic/chat/" + message.getRoomId(), message);
    }

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }
    
    @MessageMapping("/chat.getRooms")
    public void getRooms() {
        List<String> rooms = chatService.getAllDistinctRoomIds();  // 중복 없이 방 목록 가져오기
        System.out.println("Sending room list: " + rooms);  // 로그 추가
        messagingTemplate.convertAndSend("/topic/roomList", rooms);  // 클라이언트에 방 목록 전송
    }

    @GetMapping("/api/chat/history/{roomId}")
    @ResponseBody
    public List<ChatMessage> getChatHistory(@PathVariable("roomId")String roomId) {
        return chatService.getMessages(roomId);
    }


    


}
