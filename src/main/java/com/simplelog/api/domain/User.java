package com.simplelog.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    public User(Long id, String nickname, String email, String socialCode, String profileMessage) {
        this(nickname, email, socialCode, profileMessage);
        this.id = id;
    }

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }
}
