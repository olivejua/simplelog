package com.simplelog.api.commons.upload;

import static com.simplelog.api.utils.ImageUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.simplelog.api.domain.Post;
import com.simplelog.api.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3UploadService implements UploadService {
    private final S3Uploader s3Uploader;

    /***************************************************************************************************
     * Upload
     **************************************************************************************************/

    public String upload(User user, MultipartFile uploadFile) {
        String savedPath = getImagePath(user, uploadFile);
        String uploadedUrl = s3Uploader.upload(uploadFile, savedPath);

        user.updateProfileImage(uploadedUrl);
        return uploadedUrl;
    }

    public List<String> upload(Post post, List<MultipartFile> uploadFiles) {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            String savedPath = getImagePath(post, uploadFile);
            String imageUrl = s3Uploader.upload(uploadFile, savedPath);
            uploadedUrls.add(imageUrl);
        }

        post.addImages(uploadedUrls);
        return uploadedUrls;
    }

    /***************************************************************************************************
     * Remove
     **************************************************************************************************/

    public void remove(User user) {
        if (!user.hasProfileImage()) {
            log.info("삭제할 이미지가 없습니다.");
            return;
        }

        String path = extractPathFromUrl(user.getProfileImageUrl(), USER_PROFILE_PATH);
        s3Uploader.remove(path);
        user.removeProfileImage();
    }

    public int removeAll(Post post) {
        if (!post.hasImages()) {
            log.info("삭제할 이미지가 없습니다.");
            return 0;
        }

        List<String> paths = mapToPaths(post.getImageUrls(), POST_IMAGE_PATH);
        int sizeOfRemovedImages = s3Uploader.remove(paths);
        post.removeImagesAll();

        return sizeOfRemovedImages;
    }

    private List<String> mapToPaths(List<String> targetUrls, String domainPath) {
        return targetUrls.stream()
                .map(url -> extractPathFromUrl(url, domainPath))
                .collect(Collectors.toList());
    }

    private String extractPathFromUrl(String url, String domainPath) {
        int idx = url.lastIndexOf(domainPath);
        return url.substring(idx);
    }
}
