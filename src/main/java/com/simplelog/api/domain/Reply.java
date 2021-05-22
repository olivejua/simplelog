package com.simplelog.api.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Column(length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_reply_id")
    private Reply parentReply;

    public Reply(User user, Comment comment, String content) {
        this.user = user;
        this.comment = comment;
        this.content = content;
    }

    public Reply(User user, Comment comment, String content, Reply parentReply) {
        this.user = user;
        this.comment = comment;
        this.content = content;
        this.parentReply = parentReply;
    }
}
