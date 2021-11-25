package com.simplelog.api.commons.upload;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.StringUtils;

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

    public String upload(MultipartFile file, String path) {
        return s3Uploader.upload(file, path);
    }

    public List<String> upload(Map<String, MultipartFile> filePathMap) {
        return filePathMap.entrySet().stream()
            .map(entry -> {
                MultipartFile file = entry.getValue();
                String path = entry.getKey();

                return upload(file, path);
            })
            .collect(Collectors.toList());
    }

    /***************************************************************************************************
     * Remove
     **************************************************************************************************/

    public void remove(String path) {
        if (StringUtils.isNullOrEmpty(path)) {
            log.info("삭제할 이미지가 없습니다.");
            return;
        }

        s3Uploader.remove(path);
    }

    public int remove(List<String> paths) {
        if (paths==null || paths.isEmpty()) {
            log.info("삭제할 이미지가 없습니다.");
            return 0;
        }

        return s3Uploader.remove(paths);
    }
}
