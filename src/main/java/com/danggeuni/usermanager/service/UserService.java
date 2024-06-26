package com.danggeuni.usermanager.service;

import com.danggeuni.usermanager.controller.dto.UpdateUserRequestDto;
import com.danggeuni.usermanager.utils.Encryption;
import com.danggeuni.usermanager.controller.dto.RegisterUserRequestDto;
import com.danggeuni.usermanager.controller.dto.UserResponseDto;
import com.danggeuni.usermanager.domain.entity.UserEntity;
import com.danggeuni.usermanager.domain.repository.UserRepository;
import com.danggeuni.usermanager.utils.RegularExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private RegularExpression regularExpression;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void joinUser(RegisterUserRequestDto user) {
        UserEntity userData = user.toEntity();

        // userId 중복 검사
        UserEntity isExistUserId = userRepository.findByUserId(userData.getUserId());
        if(isExistUserId != null) {
            throw new IllegalArgumentException("이미 가입된 사용자 아이디 입니다.");
        }

        // email 중복 검사
        UserEntity isExistEmail = userRepository.findByEmail(userData.getEmail());
        if(isExistEmail != null) {
            throw new IllegalArgumentException("이미 가입된 이메일 주소입니다.");
        }

        // nickname 길이 제한
        if(userData.getNickname().length() > 15) {
            throw new IllegalArgumentException("nickname 길이는 15자를 넘을 수 없습니다.");
        }

        // phone 번호 정규식 검증
        if(!Pattern.matches(RegularExpression.PHONE, userData.getPhone())) {
            throw new IllegalArgumentException("유효하지 않은 전화번호 형식입니다.");
        }

        // email 정규식 검증
        if (!RegularExpression.checkEmail(user.getEmail())) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
        }

        // password 암호화
        Encryption hashPwd = new Encryption();
        String newPassword = hashPwd.getEncrypt(userData.getPassword());

        userData.encryptedPwd(newPassword);

        userRepository.save(user.toEntity());
        log.info("join service layer 동작 완료");
    }

    public Page<UserResponseDto> findUsers(int page, int pageSize, String sort) {
        if(sort.equals("id") | sort.equals("name")) {
            int sendPage = page - 1;
            // 비밀번호 제거 (Entity > DTO 변경)
            Page<UserEntity> lists = userRepository.findAll(PageRequest.of(sendPage, pageSize, Sort.by(Sort.Direction.ASC, sort)));
            Page<UserResponseDto> userResponseDto = lists.map(user -> new UserResponseDto(user.getId(), user.getUserId(), user.getNickname(), user.getName(), user.getPhone(), user.getEmail()));

            return userResponseDto;
        } else {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }
    }

    @Transactional
    public UserEntity update(String userId, UpdateUserRequestDto dto) {

        UserEntity user = userRepository.findByUserId(userId);
        System.out.println(user + "존재합니까");

        // nickname 길이 제한
        if(dto.getNickname().length() > 15) {
            throw new IllegalArgumentException("nickname 길이는 15자를 넘을 수 없습니다.");
        }

        // phone 번호 정규식 검증
        if(!RegularExpression.checkPhone(dto.getPhone())) {
            throw new IllegalArgumentException("유효하지 않은 전화번호 형식입니다.");
        }

        // password 암호화
        Encryption hashPwd = new Encryption();
        String newPassword = hashPwd.getEncrypt(dto.getPassword());

        user.update(newPassword, dto.getNickname(), dto.getName(), dto.getPhone());

        return user;
    }
}
