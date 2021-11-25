package com.simplelog.api.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

    @Embedded
    private Profile profile;

    public User(String nickname, String email, String socialCode, Profile profile) {
        this.nickname = nickname;
        this.email = email;
        this.socialCode = socialCode;
        this.profile = profile;
    }

    public User(Long id, String nickname, String email, String socialCode, Profile profile) {
        this(nickname, email, socialCode, profile);
        this.id = id;
    }

    /**
     * Update
     */
    public void updateProfileImage(String imageUrl) {
        profile = new Profile(profile.getMessage(), imageUrl);
    }

    public void removeProfileImage() {
        profile = new Profile(profile.getMessage());
    }

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }

    public String getProfileImagePath() {
        return profile.getImagePath();
    }

    /**
     * Confirm
     */
    public boolean hasProfileImage() {
        return profile.hasImage();
    }
}
