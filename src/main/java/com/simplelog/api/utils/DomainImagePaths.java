package com.simplelog.api.utils;

import java.util.List;
import java.util.UUID;

public abstract class DomainImagePaths {
    private final static String SEP = "/";

    public final static String USER_PROFILE_PATH = "user" + SEP + "profile" + SEP;
    public final static String POST_IMAGE_PATH = "post" + SEP;

    private final static List<String> IMAGE_EXTENSIONS = List.of("gif", "jpeg", "jpg", "png", "bmp", "pdf", "mp4");

    public static String makeUserProfilePath(Long userId, String fileName) {
        return makePath(USER_PROFILE_PATH, userId, fileName);
    }

    public static String makePostPath(Long postId, String fileName) {
        return makePath(POST_IMAGE_PATH, postId, fileName);
    }

    private static String makePath(String domainPath, Long domainId, String fileName) {
        String extension = getExtension(fileName);
        validateImage(extension);

        return String.format("%s%s%s", domainPath, (domainId + SEP), (UUID.randomUUID() + "_" + fileName));
    }

    private static void validateImage(String extension) {
        boolean isContained = IMAGE_EXTENSIONS.contains(extension);

        if (!isContained) {
            throw new IllegalArgumentException("이미지가 아닙니다.");
        }
    }

    private static String getExtension(String fileName) {
        int idx = fileName.lastIndexOf(".");

        if (idx <= 0) {
            throw new IllegalArgumentException("확장자를 찾을 수 없습니다.");
        }

        return fileName.substring(idx+1);
    }

    public static String extractPathFromUrl(String path, String url) {
        int idx = url.lastIndexOf(path);
        return url.substring(idx);
    }
}
