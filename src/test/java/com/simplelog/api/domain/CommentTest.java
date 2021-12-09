package com.simplelog.api.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @DisplayName("댓글을 생성할 수 있다")
    @Test
    void createComment() {
        //given
        String content = "댓글";
        Comment comment = new Comment(null, null, content);

        //when
        String actual = comment.getContent();

        //then
        assertEquals(content, actual);
    }
}