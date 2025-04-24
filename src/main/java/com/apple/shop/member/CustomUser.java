package com.apple.shop.member;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public  class CustomUser extends User {
    public Long id;
    public String displayName;

    public CustomUser(String username,
                      String password,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    // 만약 명시적으로 setter를 추가하고 싶다면 아래와 같이 작성할 수 있습니다.
     public void setDisplayName(String displayName) {
         this.displayName = displayName;
     }

     public void setId(Long id) {
         this.id = id;
     }
}
