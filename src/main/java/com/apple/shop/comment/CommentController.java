package com.apple.shop.comment;

import com.apple.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    //댓글 넣는방법
    @PostMapping("/comment")
    String postComment(@RequestParam(name = "content") String content,               // 댓글 내용
    		@RequestParam(name = "parent") Long parent,                   // 부모 ID (댓글이 달리는 대상의 ID)
    		@RequestParam(name = "type") String type,                     // 댓글 유형 (어떤 페이지의 댓글인지)
    		@RequestParam(name = "referer", required = false) String referer,  // 이전 페이지의 URL (optional)
                       Authentication auth) {
        CustomUser user = (CustomUser) auth.getPrincipal();

        var data = new Comment();
        data.setContent(content);
        data.setUsername(user.getUsername());
        data.setParentId(parent);
        data.setType(type);  // 댓글 유형 저장
        data.setCreatedAt(new Date()); //현재 날짜 저장하는 코드

        commentRepository.save(data);

        return "redirect:" + (referer != null ? referer : "/list");
    }



}
