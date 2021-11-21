package com.simplelog.api.commons.upload;

import com.simplelog.api.domain.Post;
import com.simplelog.api.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {
    /**
     * user의 프로필 이미지를 업로드한다. 업로드 후에는 파라미터의 user에 프로필 이미지 정보가 저장된다.
     * @param user 업로드할 프로필 이미지를 가지는 user
     * @param uploadFile 업로드 이미지 파일
     * @return 업로드한 이미지 Url
     */
    String upload(User user, MultipartFile uploadFile);

    /**
     * post의 첨부이미지를 업로드한다. 업로드 후에는 파라미터의 post의 첨부이미지 목록 정보가 저장된다.
     * @param post 첨부이미지가 속해있는 post
     * @param uploadFiles 업로드 이미지 파일
     * @return 업로드한 이미지 url 목록
     */
    List<String> upload(Post post, List<MultipartFile> uploadFiles) ;

    /**
     * user의 프로필 이미지를 삭제한다. 삭제 후에는 파라미터의 user에 프로필 이미지가 삭제된다.
     * @param user 삭제할 프로필 이미지를 가진 user
     */
    void remove(User user);

    /**
     * post의 첨부이미지 목록을 모두 삭제한다. 삭제 후에는 파라미터의 post에 있는 첨부이미지 정보가 모두 삭제된다.
     * @param post 삭제할 첨부이미지 목록을 가지고 있는 post
     * @return 삭제된 이미지 개수
     */
    int removeAll(Post post);
}
