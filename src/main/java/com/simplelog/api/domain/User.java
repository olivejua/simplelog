package com.simplelog.api.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 45)
    private String nickname;

    @Column(nullable = false, length = 70)
    private String email;

    @Column(nullable = false, length = 20)
    private String socialCode;

    @Column(nullable = false, length = 300)
    private String profileMessage;

    public User(String nickname, String email, String socialCode, String profileMessage) {
        this.nickname = nickname;
        this.email = email;
        this.socialCode = socialCode;
        this.profileMessage = profileMessage;
    }
}
