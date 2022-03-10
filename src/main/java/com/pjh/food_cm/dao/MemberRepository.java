package com.pjh.food_cm.dao;

import com.pjh.food_cm.config.Role;
import com.pjh.food_cm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {   //DB랑만 연결되는 클래스
    Optional<Member> findByLoginId(String loginId); //jpa에서 이 메소드를 optional로 지정했기때문에 optional로함
    boolean existsByLoginId(String loginId);

    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);

    Long countTodayMember();

    Long countByAuthorityLike(Role authority);
}
