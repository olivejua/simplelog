package com.simplelog.api.domain;

import static com.simplelog.api.utils.DomainImagePaths.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.BatchSize;

import lombok.Getter;

@Getter
@Embeddable
public class PostImages {
    @ElementCollection
    @CollectionTable(name = "post_image",
            joinColumns = @JoinColumn(name = "post_id"))
    @BatchSize(size = 30)
    @Column(name = "image_url", nullable = false)
    private final List<String> imageUrls = new ArrayList<>();

    public PostImages() {}

    public void addAll(List<String> images) {
        imageUrls.addAll(images);
    }

    public void removeAll() {
        imageUrls.clear();
    }

    public boolean hasImages() {
        return !imageUrls.isEmpty();
    }

    public List<String> getPaths() {
        return imageUrls.stream()
            .map(url -> extractPathFromUrl(POST_IMAGE_PATH, url))
            .collect(Collectors.toList());
    }
}
