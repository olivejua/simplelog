package com.simplelog.api.domain;

import javax.persistence.*;

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

    @Embedded
    private Profile profile;

    public User(String nickname, String email, String socialCode, String profileMessage) {
        this.nickname = nickname;
        this.email = email;
        this.socialCode = socialCode;
        this.profile = new Profile(profileMessage);
    }

    public User(Long id, String nickname, String email, String socialCode, String profileMessage) {
        this(nickname, email, socialCode, profileMessage);
        this.id = id;
    }

    /**
     * update
     */
    public void updateProfileImage(String imageUrl) {
        profile.updateImageUrl(imageUrl);
    }

    public void removeProfileImage() {
        profile.removeImageUrl();
    }

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }
}
