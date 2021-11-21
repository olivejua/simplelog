package com.simplelog.api.unit.utils;

import com.simplelog.api.common.factory.MockUser;
import com.simplelog.api.domain.User;
import com.simplelog.api.utils.ImageUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ImageUtilsTest {

    private User mockUser;

    @BeforeEach
    void setup() {
        mockUser = MockUser.builder()
                .id(1L)
                .nickname("Ksk")
                .email("test@email.com")
                .profileMessage("hi")
                .build();
    }

    @DisplayName("확장자가 이미지가 맞으면 정상적으로 작동한다")
    @ParameterizedTest
    @ValueSource(strings = {"jpg", "png", "gif", "pdf", "jpeg"})
    void testValidateImages(String extension) throws IOException {
        MockMultipartFile mockImage = getMockMultipartFile("testImage", extension);

        assertDoesNotThrow(() -> ImageUtils.getImagePath(mockUser, mockImage));
    }

    @DisplayName("확장자가 이미지가 아니라면 예외가 발생한다")
    @Test
    void testValidateImages() throws IOException {
        MockMultipartFile mockTextFile = getMockMultipartFile("testText", "txt");

        Assertions.assertThrows(IllegalArgumentException.class, () -> ImageUtils.getImagePath(mockUser, mockTextFile));
    }


    private MockMultipartFile getMockMultipartFile(String fileName, String extension) throws IOException {
        String path = "src/test/resources/images/testImage.jpg";
        FileInputStream fileInputStream = new FileInputStream(path);

        return new MockMultipartFile(fileName, fileName + "." + extension, "image/jpeg", fileInputStream);
    }
}
