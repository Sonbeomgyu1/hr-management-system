package com.apple.shop;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptionExample {
    public static void main(String[] args) {
        // BCryptPasswordEncoder 객체 생성
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 원본 비밀번호
        String rawPassword = "123";  // 원하는 비밀번호 입력

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // 암호화된 비밀번호 출력
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
