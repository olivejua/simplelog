package com.simplelog.api.domain;

import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class PostImages {
    @ElementCollection
    @CollectionTable(name = "post_image",
            joinColumns = @JoinColumn(name = "post_id"))
    @BatchSize(size = 30)
    @Column(name = "image_url", nullable = false)
    private List<String> imageUrls;

    public PostImages() {
        imageUrls = new ArrayList<>();
    }

    public void addAll(List<String> images) {
        imageUrls.addAll(images);
    }

    public void removeAll() {
        imageUrls.clear();
    }

    public boolean isNotEmpty() {
        return !imageUrls.isEmpty();
    }
}
