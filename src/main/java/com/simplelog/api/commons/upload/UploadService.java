package com.simplelog.api.commons.upload;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /**
     * 단일 파일을 업로드한다.
     * @param path 업로드할 이미지 경로
     * @param file 업로드 이미지 파일
     * @return 업로드한 이미지 Url
     */
    String upload(MultipartFile file, String path);

    /**
     * 여러 파일을 업로드한다.
     * @param filePathMap key: 업로드할 이미지 경로 / value: 업로드할 이미지 파일
     * @return 업로드한 이미지 url 목록
     */
    List<String> upload(Map<String, MultipartFile> filePathMap);

    /**
     * 단일 파일을 삭제한다.
     * @param path 삭제할 파일 경로 (S3경로를 제외한 DomainPath 예)user/profile/testImages.jpg)
     */
    void remove(String path);

    /**
     * 여러 파일을 삭제한다.
     * @param paths 삭제할 파일 경로 목록
     * @return 삭제된 이미지 개수
     */
    int remove(List<String> paths);
}
