package com.simplelog.api.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReplyContent {

    static final int MAX_LENGTH = 500;

    @Column(nullable = false, length = MAX_LENGTH)
    private String content;

    public CommentReplyContent(String content) {
        if (invalid(content)) {
            throw new IllegalArgumentException("content 길이가 유효하지 않습니다.");
        }
        this.content = content;
    }

    private boolean invalid(String content) {
        return content == null || content.isEmpty() || content.length() > MAX_LENGTH;
    }

    public String get() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentReplyContent)) return false;
        CommentReplyContent that = (CommentReplyContent) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
