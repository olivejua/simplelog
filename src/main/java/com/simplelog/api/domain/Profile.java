package com.simplelog.api.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Profile {
    @Column(name = "profile_message", nullable = false, length = 300)
    private String message;

    @Column(name = "profile_url")
    private String imageUrl;

    public Profile() {
    }

    public Profile(String message) {
        this.message = message;
    }

    public Profile(String message, String imageUrl) {
        this(message);
        this.imageUrl = imageUrl;
    }

    public void updateImageUrl(String url) {
        imageUrl = url;
    }

    public void removeImageUrl() {
        imageUrl = null;
    }

    public boolean hasImage() {
        return StringUtils.hasText(imageUrl);
    }
}
