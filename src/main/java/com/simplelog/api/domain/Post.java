package com.simplelog.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 2200)
    private String content;

    @Embedded
    private PostTags tags = new PostTags();

    @Embedded
    private PostImages images = new PostImages();

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public Post(Long id, User user, String content) {
        this(user, content);
        this.id = id;
    }

    /**
     * Update
     */
    public void addTags(List<String> tags) {
        this.tags.addAll(this, tags);
    }

    public void addImages(List<String> images) {
        this.images.addAll(images);
    }

    public void removeImagesAll() {
        images.removeAll();
    }

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }

    public PostTags getTags() {
        return tags;
    }

    public List<String> getImagePaths() {
        return images.getPaths();
    }

    /**
     * Confirm
     */
    public boolean hasTags() {
        return tags.exists();
    }

    public boolean hasImages() {
        return images.hasImages();
    }
}
