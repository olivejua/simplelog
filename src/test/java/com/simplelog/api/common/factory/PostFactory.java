package com.simplelog.api.common.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import com.simplelog.api.domain.Post;
import com.simplelog.api.domain.PostTags;
import com.simplelog.api.domain.User;
import com.simplelog.api.repository.PostRepository;
import com.simplelog.api.repository.PostTagRepository;

@TestComponent
public class PostFactory {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostTagRepository tagRepository;

	public Post post(User user) {
		Post post = createPost(user);
		return save(post);
	}

	public Post post(User user, List<String> tags) {
		Post post = createPost(user);
		return save(post);
	}

	private Post save(Post post) {
		Post savedPost = postRepository.save(post);
		saveTags(post);

		return savedPost;
	}

	private void saveTags(Post post) {
		if (!post.hasTags()) {
			return;
		}

		PostTags postTags = post.getTags();
		tagRepository.saveAll(postTags.getAll());
	}

	private static Post createPost(User user) {
		return MockPost
			.builder()
			.user(user)
			.build();
	}
}