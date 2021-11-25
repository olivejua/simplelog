package com.simplelog.api.domain;

import static com.simplelog.api.utils.DomainImagePaths.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
@Embeddable
public class Profile {
    @Column(name = "profile_message", nullable = false, length = 300)
    private String message;

    @Column(name = "profile_url")
    private String imageUrl;

    protected Profile() {
    }

    public Profile(String message) {
        this.message = message;
    }

    public Profile(String message, String imageUrl) {
        this(message);
        this.imageUrl = imageUrl;
    }

    public boolean hasImage() {
        return StringUtils.hasText(imageUrl);
    }

    public String getImagePath() {
        if (hasImage()) {
            return extractPathFromUrl(USER_PROFILE_PATH, imageUrl);
        }

        return imageUrl;
    }
}
