package com.apple.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) //권한을 주고 이거 설정안하면 다 들어가짐 1번
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()); // 배포하기 전에는 이거 켜야 함. 상대방이 맘대로 글 올릴 수 있음.

        // 정적 리소스와 로그인, 회원가입 경로 접근 허용
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/login", "/register","/contracts/**","/projects/**","/chat/**", "/css/**", "/js/**", "/images/**").permitAll() // 로그인, 회원가입, 정적 리소스 허용
                // 관리자만 /admin/** 경로 접근 가능 2번 이후  MyUserDetailsService 에서 설정
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
        );

        // 로그인 설정
        http.formLogin((formLogin) -> formLogin
                .loginPage("/login")  // 로그인 페이지 경로
                .defaultSuccessUrl("/", true) // 로그인 성공 시 홈으로 이동
                .permitAll() // 로그인 페이지 접근 허용
        );

        // 로그아웃 설정
        http.logout(logout -> logout
                .logoutUrl("/logout") // 로그아웃 URL
                .logoutSuccessUrl("/login") // 로그아웃 후 로그인 페이지로 이동
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // 쿠키 삭제
        );

        // 기본 페이지 요청시 /login 페이지로 리디렉션
        http.formLogin(formLogin -> formLogin.loginPage("/login"));

        return http.build();
    }
}
