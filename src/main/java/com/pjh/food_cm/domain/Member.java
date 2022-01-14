package com.pjh.food_cm.domain;

import com.pjh.food_cm.config.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //순서대로 정렬을 한다
    private Long id;

    private String loginId;
    private String loginPw;
    private String name;
    private String nickname;
    private String email;

    private boolean isAccountNonExpired= true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired=true;
    private boolean isEnabled=true;




    public static Member createMember(String loginId, String loginPw, String name,String nickname,String email,Role authority){
        Member member=new Member();
        member.loginId=loginId;
        member.loginPw=loginPw;
        member.name=name;
        member.nickname=nickname;
        member.email=email;
        member.authority=authority;
        return member;
    }


    @Enumerated(EnumType.STRING)
    private Role authority;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.authority.getValue()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
