package com.simplelog.api.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee", nullable = false)
    private User followee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower", nullable = false)
    private User follower;

    public Follow(User followee, User follower) {
        this.followee = followee;
        this.follower = follower;
    }
}
