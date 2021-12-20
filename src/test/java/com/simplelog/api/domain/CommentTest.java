package com.simplelog.api.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @DisplayName("Comment 내용은 CommentReplyContent 문자열 값을 반환한다")
    @Test
    void createComment_getContent_returnsStringValueOfContent() {
        //given
        String commentContent = "댓글";
        CommentReplyContent content = new CommentReplyContent(commentContent);

        //when
        Comment comment = new Comment(null, null, content);

        //then
        assertEquals(commentContent, comment.getContent());
    }
}