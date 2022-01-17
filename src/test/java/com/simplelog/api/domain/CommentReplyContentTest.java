package com.simplelog.api.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.simplelog.api.domain.CommentReplyContent.MAX_LENGTH;
import static org.junit.jupiter.api.Assertions.*;

class CommentReplyContentTest {

    @DisplayName("CommentReplyContent를 생성할 수 있다")
    @Test
    void create() {
        //given
        String content = "a".repeat(MAX_LENGTH);

        //when
        CommentReplyContent actual = new CommentReplyContent(content);

        //then
        assertEquals(content, actual.get());
    }

    @DisplayName("내용이 null이면 예외가 발생한다")
    @Test
    public void create_nullContent_throwException() throws Exception {
        //given
        String content = null;

        //when
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new CommentReplyContent(content));

        //then
        assertEquals("content 길이가 유효하지 않습니다.", exception.getMessage());
    }

    @DisplayName("내용이 빈 문자열이면 예외가 발생한다")
    @Test
    public void create_emptyContent_throwException() throws Exception {
        //given
        String content = "";

        //when
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new CommentReplyContent(content));

        //then
        assertEquals("content 길이가 유효하지 않습니다.", exception.getMessage());
    }

    @DisplayName("내용이 최대 길이를 초과하면 예외가 발생한다 - 영어")
    @Test
    public void create_exceedEnContentLengthLimit_throwException() throws Exception {
        //given
        String content = "a".repeat(MAX_LENGTH + 1);

        //when
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new CommentReplyContent(content));

        //then
        assertEquals("content 길이가 유효하지 않습니다.", exception.getMessage());
    }

    @DisplayName("내용이 최대 길이를 초과하면 예외가 발생한다 - 한국어")
    @Test
    public void create_exceedKoContentLengthLimit_throwException() throws Exception {
        //given
        String content = "가".repeat(MAX_LENGTH + 1);

        //when
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new CommentReplyContent(content));

        //then
        assertEquals("content 길이가 유효하지 않습니다.", exception.getMessage());
    }
}