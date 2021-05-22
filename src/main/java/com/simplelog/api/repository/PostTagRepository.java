package com.simplelog.api.repository;

import com.simplelog.api.domain.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}
