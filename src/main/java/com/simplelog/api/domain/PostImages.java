package com.simplelog.api.domain;

import java.util.ArrayList;
import java.util.List;

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
}
