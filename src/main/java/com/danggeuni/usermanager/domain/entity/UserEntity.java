package com.danggeuni.usermanager.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "userId", nullable = false, unique = true, updatable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickName", nullable = false)
    private String nickname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name= "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;


    public UserEntity(Long id, String userId, String password, String nickname, String name, String phone, String email, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public void encryptedPwd(String hashedPwd){
        this.password = hashedPwd;
    }

    public void update(String password, String nickname, String name, String phone) {
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.updateDate = LocalDateTime.now();
    }
}
