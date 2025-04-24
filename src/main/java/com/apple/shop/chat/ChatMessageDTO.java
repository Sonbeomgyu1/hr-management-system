package com.apple.shop.chat;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDTO {
    private String roomId;
    private String sender;
    private String content;
}