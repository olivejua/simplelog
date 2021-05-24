package com.simplelog.api;

import com.simplelog.api.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class InsertDataTest {

    @Autowired
    EntityManager em;

    @Test
    void saveUser() {
        User user = new User("nickname1", "user1@gmail.com", "google", "example message1");
        em.persist(user);
    }

    @Test
    void savePost() {
        User user = new User("nickname1", "user1@gmail.com", "google", "example message1");
        em.persist(user);

        Post post = new Post(user, "content1");
        em.persist(post);
    }

    @Test
    void saveTags() {
        User user = new User("nickname1", "user1@gmail.com", "google", "example message1");
        em.persist(user);

        Post post = new Post(user, "post content1");
        em.persist(post);

        PostTag tag1 = new PostTag(post, "tag1");
        PostTag tag2 = new PostTag(post, "tag2");
        em.persist(tag1);
        em.persist(tag2);
    }

    @Test
    void saveLikes() {
        User user = new User("nickname1", "user1@gmail.com", "google", "example message1");
        em.persist(user);

        Post post = new Post(user, "post content1");
        em.persist(post);

        Likes like  = new Likes(post, user);
        em.persist(like);
    }

    @Test
    void saveComment() {
        User postWriter = new User("post writer", "user1@gmail.com", "google", "example message1");
        Post post = new Post(postWriter, "post content1");
        em.persist(postWriter);
        em.persist(post);

        User commentWriter = new User("comment writer", "user2@gmail.com", "google", "example message2");
        Comment comment = new Comment(commentWriter, post, "comment content1");
        em.persist(commentWriter);
        em.persist(comment);
    }

    @Test
    void saveReply() {
        User postWriter = new User("post writer", "user1@gmail.com", "google", "example message1");
        Post post = new Post(postWriter, "post content1");
        em.persist(postWriter);
        em.persist(post);

        User commentWriter = new User("comment writer", "user2@gmail.com", "google", "example message2");
        Comment comment = new Comment(commentWriter, post, "comment content1");
        em.persist(commentWriter);
        em.persist(comment);

        User replyWriter1 = new User("reply writer1", "user3@gmail.com", "google", "example message3");
        Reply parentReply = new Reply(replyWriter1, comment, "reply content1");
        em.persist(replyWriter1);
        em.persist(parentReply);

        User replyWriter2 = new User("reply writer2", "user4@gmail.com", "google", "example message4");
        Reply childReply = new Reply(replyWriter2, comment, "reply content2", parentReply);
        em.persist(replyWriter2);
        em.persist(childReply);
    }

    @Test
    void saveFollow() {
        User followee = new User("followee", "user1@gmail.com", "google", "example message1");
        em.persist(followee);

        User follower = new User("follower", "user2@gmail.com", "google", "example message2");
        em.persist(follower);

        Follow follow = new Follow(followee, follower);
        em.persist(follow);
    }
}
