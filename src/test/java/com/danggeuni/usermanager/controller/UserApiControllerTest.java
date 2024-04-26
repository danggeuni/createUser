package com.danggeuni.usermanager.controller;

import com.danggeuni.usermanager.controller.dto.RegisterUserRequestDto;
import com.danggeuni.usermanager.controller.dto.UpdateUserRequestDto;
import com.danggeuni.usermanager.controller.dto.UserResponseDto;
import com.danggeuni.usermanager.domain.entity.UserEntity;
import com.danggeuni.usermanager.domain.repository.UserRepository;

import com.danggeuni.usermanager.service.UserService;
import com.danggeuni.usermanager.utils.Encryption;
import com.danggeuni.usermanager.utils.RegularExpression;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserApiControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    //    join user test
    @Test
    @DisplayName("joinUser : 회원 가입 성공")
    public void joinUserTest() throws Exception {
        // given
        Encryption encryption = new Encryption();

        // case : success
        RegisterUserRequestDto userRequestDto = new RegisterUserRequestDto(encryption.getEncrypt("password"), "12345", "name", "010-1234-5678", "1@naver.com");
        // case : failed
        // RegisterUserRequestDto userRequestDto = new RegisterUserRequestDto("password", "123456789123456789", "name", "010-1234-5678", "1@naver.com");
        // RegisterUserRequestDto userRequestDto = new RegisterUserRequestDto("password", "12345", "name", "010-71234-5678", "1@naver.com");
        // RegisterUserRequestDto userRequestDto = new RegisterUserRequestDto("password", "12345", "name", "010-1234-5678", "1navercom");

        // when
        userService.joinUser(userRequestDto);

        // then
        Optional<UserEntity> result = userRepository.findById(1L);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getPassword()).isEqualTo(encryption.getEncrypt("password"));
        assertThat(result.get().getNickname()).isEqualTo(userRequestDto.getNickname());
        assertThat(result.get().getPhone()).isEqualTo(userRequestDto.getPhone());
        assertThat(result.get().getEmail()).isEqualTo(userRequestDto.getEmail());
    }

    @Test
    @DisplayName("selectAll : 회원 정보 조회 성공")
    public void selectAllTest() throws Exception {
        // given
        UserEntity entity1 = new UserEntity(1L, "password", "nickname", "name", "010-1234-5678", "1@naver.com", LocalDateTime.now(), LocalDateTime.now());
        UserEntity entity2 = new UserEntity(2L, "password", "nickname", "name", "010-1234-5678", "2@naver.com", LocalDateTime.now(), LocalDateTime.now());

        // when
        userRepository.save(entity1);
        userRepository.save(entity2);
        List<UserEntity> result = userRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("modifyUser : 회원 정보 수정 성공")
    public void modifyUserTest() throws Exception {
        // given
        UserEntity entity = new UserEntity(1L, "password", "nickname", "name", "010-1234-5678", "1@naver.com", LocalDateTime.now(), LocalDateTime.now());
        userRepository.save(entity);

        Encryption encryption = new Encryption();

        // case : success
        UpdateUserRequestDto userRequestDto = new UpdateUserRequestDto("1234", "danggeuni", "juno", "010-8765-4321");
        // case : failed
        // UpdateUserRequestDto userRequestDto = new UpdateUserRequestDto("1234", "1234567891234567", "juno", "010-8765-4321");
        // UpdateUserRequestDto userRequestDto = new UpdateUserRequestDto("1234", "danggeuni", "juno", "0108765-4321");

        String pwd = encryption.getEncrypt(userRequestDto.getPassword());

        // when
        UserEntity user = userService.update(1L, userRequestDto);

        // then
        assertThat(user.getPassword()).isEqualTo(pwd);
        assertThat(user.getNickname()).isEqualTo(userRequestDto.getNickname());
        assertThat(user.getName()).isEqualTo(userRequestDto.getName());
        assertThat(user.getPhone()).isEqualTo(userRequestDto.getPhone());
    }
}