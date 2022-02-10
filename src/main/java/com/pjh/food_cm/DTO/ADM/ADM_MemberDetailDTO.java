package com.pjh.food_cm.DTO.ADM;


import com.pjh.food_cm.DTO.article.ArticleListDTO;
import com.pjh.food_cm.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ADM_MemberDetailDTO {
    private Long memberId;
    private String loginId;
    private String nickname;
    private String name;
    private LocalDateTime regDate;

    private List<ArticleListDTO> articles;

    public ADM_MemberDetailDTO(Member member,List<ArticleListDTO> articles){
        this.memberId=member.getId();
        this.loginId=member.getLoginId();
        this.nickname=member.getNickname();
        this.name=member.getName();
        this.regDate=member.getRegDate();
        this.articles=articles;
    }
}

































