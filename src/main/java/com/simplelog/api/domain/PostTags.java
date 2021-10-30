package com.simplelog.api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class PostTags {

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostTag> tags = new ArrayList<>();

	public PostTags() {}

	public void add(PostTag tag) {
		tags.add(tag);
	}

	public void addAll(List<PostTag> tags) {
		this.tags.addAll(tags);
	}

	public void addAll(Post post, List<String> postTags) {
		List<PostTag> tags = postTags.stream()
			.map(tag -> new PostTag(post, tag))
			.collect(Collectors.toList());

		this.tags.addAll(tags);
	}

	public void replace(List<PostTag> tags) {
		this.tags.clear();
		addAll(tags);
	}

	public int size() {
		return tags.size();
	}

	public List<PostTag> getAll() {
		return tags;
	}
}