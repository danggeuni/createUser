package com.danggeuni.usermanager.controller.dto;

import lombok.Getter;

@Getter
public class UpdateUserResponseDto {
    private final String nickname;
    private final String name;
    private final String phone;
    private final String email;

    public UpdateUserResponseDto(String nickname, String name, String phone, String email) {
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
