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

    @DisplayName("댓글 생성 시 글자 수 제한을 초과하면 예외가 발생한다 - 영어")
    @Test
    public void createComment_exceedEnContentLengthLimit_throwException() throws Exception {
        //given
        String content = "a".repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class,
                () -> new Comment(null, null, content));
    }

    @DisplayName("댓글 생성 시 글자 수 제한을 초과하면 예외가 발생한다 - 한국어")
    @Test
    public void createComment_exceedKoContentLengthLimit_throwException() throws Exception {
        //given
        String content = "가".repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class,
                () -> new Comment(null, null, content));
    }
}