package com.pjh.food_cm.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private String value;  //Role.Member로 하면 value값에 member가 담겨져 사용함
}
