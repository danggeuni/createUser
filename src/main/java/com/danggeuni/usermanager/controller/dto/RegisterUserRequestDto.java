package com.danggeuni.usermanager.controller.dto;

import com.danggeuni.usermanager.domain.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RegisterUserRequestDto {
    private String userId;
    private String password;
    private String nickname;
    private String name;
    private String phone;
    private String email;

    public UserEntity toEntity(){
        return new UserEntity(null, userId, password, nickname, name, phone, email, LocalDateTime.now(), LocalDateTime.now());
    }

    public RegisterUserRequestDto(){}

    public RegisterUserRequestDto(String userId, String password, String nickname, String name, String phone, String email) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
