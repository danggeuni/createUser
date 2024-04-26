package com.danggeuni.usermanager.controller;

import com.danggeuni.usermanager.controller.dto.RegisterUserRequestDto;
import com.danggeuni.usermanager.controller.dto.UpdateUserRequestDto;
import com.danggeuni.usermanager.controller.dto.UpdateUserResponseDto;
import com.danggeuni.usermanager.controller.dto.UserResponseDto;
import com.danggeuni.usermanager.domain.entity.UserEntity;
import com.danggeuni.usermanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user/join")
    public ResponseEntity<String> joinUser(@RequestBody RegisterUserRequestDto user) {
        try {
            userService.joinUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("에러 발생", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/user/list")
    public ResponseEntity<?> getUsers(@RequestParam(required = false, defaultValue = "1") int page,
                                                          @RequestParam(required = false, defaultValue = "5") int pageSize,
                                                          @RequestParam(required = false, defaultValue = "id") String sort) {
        try {
            Page<UserResponseDto> userResponseDto = userService.findUsers(page, pageSize, sort);
            return ResponseEntity.ok().body(userResponseDto);
        } catch (Exception e) {
            log.error("에러 발생", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/api/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        try {
            UserEntity update = userService.update(userId, updateUserRequestDto);
            UpdateUserResponseDto updateUserResponseDto = new UpdateUserResponseDto(update.getNickname(), update.getName(), update.getPhone(), update.getEmail());
            return ResponseEntity.ok().body(updateUserResponseDto);
        } catch (Exception e) {
            log.error("에러 발생", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
