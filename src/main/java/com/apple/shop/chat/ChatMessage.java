package com.apple.shop.chat;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;
    private String sender;
    private String content;
    private LocalDateTime sendTime;
    
    @JsonProperty("room_id")  // JsonProperty 어노테이션을 통해 응답 필드 이름을 설정
    public String getRoomId() {
        return roomId;
    }

    @JsonProperty("send_time")
    public LocalDateTime getSendTime() {
        return sendTime;
    }
}