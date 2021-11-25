package com.simplelog.api.domain;

import java.util.List;

import javax.persistence.*;

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
    private PostTags postTags = new PostTags();

    @Embedded
    private PostImages images = new PostImages();

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public Post(Long id, User user, String content, List<String> inputTags) {
        this(user, content);
        this.id = id;
        addTags(inputTags);
    }

    /**
     * Update
     */
    public void addTags(List<String> inputTags) {
        this.postTags.addAll(this, inputTags);
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

    public PostTags getPostTags() {
        return postTags;
    }

    public List<String> getImageUrls() {
        return images.getImageUrls();
    }

    /**
     * Confirm
     */
    public boolean hasTags() {
        return postTags.exists();
    }

    public boolean hasImages() {
        return images.hasImages();
    }
}
