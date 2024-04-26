package com.danggeuni.usermanager.controller.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {
    private String password;
    private String nickname;
    private String name;
    private String phone;

    public UpdateUserRequestDto(){}

    public UpdateUserRequestDto(String password, String nickname, String name, String phone) {
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
    }
}
