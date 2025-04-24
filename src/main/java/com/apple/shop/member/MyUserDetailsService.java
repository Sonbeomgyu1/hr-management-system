package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = memberRepository.findByUsername(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("그런 아이디 없음");
        }
        var user = result.get();

        // 권한 설정 (ID가 1 또는 2인 사용자에게만 ROLE_ADMIN 부여)
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getId() == 1L || user.getId() == 2L) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        // CustomUser 객체에 권한 추가
        CustomUser customUser = new CustomUser(user.getUsername(), user.getPassword(), authorities);
        customUser.id = user.getId();
        customUser.displayName = user.getDisplayName();
// CustomUser 객체에 권한 추가 후 출력
        System.out.println("User: " + customUser.getUsername() + " 권한: " + customUser.getAuthorities());

        return customUser;
    }
}
