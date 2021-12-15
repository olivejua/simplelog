package com.simplelog.api.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false, length = 500)
    private String content;

    public Comment(User user, Post post, String content) {
        if (invalid(content)) {
            throw new IllegalArgumentException();
        }
        this.user = user;
        this.post = post;
        this.content = content;
    }

    private boolean invalid(String content) {
        return content == null || content.isEmpty() || content.length() > 500;
    }

    public String getContent() {
        return content;
    }
}
