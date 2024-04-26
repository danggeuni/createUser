package com.danggeuni.usermanager.controller.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String nickname;
    private final String name;
    private final String phone;
    private final String email;

    public UserResponseDto(Long id, String nickname, String name, String phone, String email) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
