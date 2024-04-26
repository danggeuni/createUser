package com.danggeuni.usermanager.controller;

import com.danggeuni.usermanager.domain.entity.UserEntity;
import com.danggeuni.usermanager.domain.repository.UserRepository;

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
    UserRepository userRepository;

    //    join user test
    @Test
    @DisplayName("joinUser : 회원 가입 성공")
    public void joinUserTest() throws Exception {
        // given
        UserEntity entity = new UserEntity(1L, "password", "nickname", "name", "010-1234-5678", "1@naver.com", LocalDateTime.now(), LocalDateTime.now());

        // when
        userRepository.save(entity);

        // then
        Optional<UserEntity> result = userRepository.findById(1L);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getEmail()).isEqualTo(entity.getEmail());
    }

    @Test
    @DisplayName("selectAll : 회원 정보 조회 성공")
    @Transactional
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
}