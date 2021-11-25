package com.simplelog.api.integration.commons.upload;

import static com.simplelog.api.utils.DomainImagePaths.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.simplelog.api.common.factory.MockPost;
import com.simplelog.api.common.factory.MockUser;
import com.simplelog.api.commons.upload.UploadService;
import com.simplelog.api.domain.Post;
import com.simplelog.api.domain.User;
import com.simplelog.api.integration.IntegrationTest;

public class UploadServiceTest extends IntegrationTest {

    @Autowired
    private UploadService uploadService;

    private User mockUser;
    private Post mockPost;

    private static Long mockDataId = 4000L;

    @BeforeEach
    void setup() {
        mockUser = MockUser.builder()
                .id(mockDataId)
                .nickname("mockUser4000")
                .email("mockUser4000@gmail.com")
                .socialCode("google")
                .profileMessage("hi")
                .build();

        mockPost = MockPost.builder()
                .id(mockDataId)
                .user(mockUser)
                .content("I'm going to test upload images.")
                .postTags(List.of("test", "upload", "images"))
                .build();

        mockDataId++;
    }

    @AfterEach
    void cleanup() {
        uploadService.remove(mockUser.getProfileImagePath());
        uploadService.remove(mockPost.getImagePaths());
    }


    @Test
    @DisplayName("유저 프로필 이미지를 S3 스토리지에 업로드한다")
    void testUploadUserProfileImage() throws IOException {
        MultipartFile uploadFile = getMockImage();

        String path = makeUserProfilePath(mockUser.getId(), uploadFile.getOriginalFilename());
        String savedImageUrl = uploadService.upload(uploadFile, path);
        mockUser.updateProfileImage(savedImageUrl);

        assertFalse(savedImageUrl.isEmpty());
        assertTrue(savedImageUrl.contains(USER_PROFILE_PATH));
    }

    @Test
    @DisplayName("게시글 첨부이미지 목록을 S3 스토리지에 업로드한다")
    void testPostImageUpload() throws IOException {
        int size = 3;

        Map<String, MultipartFile> fileMap = makeMockPostImages(size);
        List<String> savedUrls = uploadService.upload(fileMap);
        mockPost.addImages(savedUrls);

        assertEquals(size, savedUrls.size());
        assertTrue(savedUrls.get(0).contains(POST_IMAGE_PATH));
        assertTrue(savedUrls.get(1).contains(POST_IMAGE_PATH));
        assertTrue(savedUrls.get(2).contains(POST_IMAGE_PATH));
    }

    private Map<String, MultipartFile> makeMockPostImages(int size) throws IOException {
        List<MultipartFile> uploadFiles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            uploadFiles.add(getMockImage());
        }

        return uploadFiles.stream()
            .collect(Collectors.toMap(
                file -> makePostPath(mockPost.getId(), file.getOriginalFilename()),
                file -> file
            ));
    }

    private MultipartFile getMockImage() throws IOException {
        String fileName = "testImage";
        String extension = "jpg";
        String path = "src/test/resources/images/testImage.jpg";

        return getMockMultipartFile(fileName, extension, ContentType.IMAGE_JPEG, path);
    }

    private MockMultipartFile getMockMultipartFile(String fileName, String extension, ContentType contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        return new MockMultipartFile(fileName,
                fileName + "." + extension,
                contentType.toString(),
                fileInputStream);
    }
}
