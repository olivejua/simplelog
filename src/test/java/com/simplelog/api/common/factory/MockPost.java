package com.simplelog.api.common.factory;

import java.util.ArrayList;
import java.util.List;

import com.simplelog.api.domain.Post;
import com.simplelog.api.domain.User;

public class MockPost {

	private MockPost() {}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private User user;
		private String content = "test content";
		private final List<String> postTags = new ArrayList<>();

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder user(User user) {
			this.user = user;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Builder postTags(List<String> tags) {
			this.postTags.addAll(tags);
			return this;
		}

		public Post build() {
			return new Post(
				id,
				user,
				content,
				postTags
			);
		}
	}
}
