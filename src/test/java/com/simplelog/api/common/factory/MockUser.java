package com.simplelog.api.common.factory;

import com.simplelog.api.domain.User;

public class MockUser {

	private MockUser() {}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private String nickname = "test nick";
		private String email = "testuser@gmail.com";
		private String socialCode = "google";
		private String profileMessage = "hi:)";

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder nickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder socialCode(String socialCode) {
			this.socialCode = socialCode;
			return this;
		}

		public Builder profileMessage(String profileMessage) {
			this.profileMessage = profileMessage;
			return this;
		}

		public User build() {
			return new User(
				id,
				nickname,
				email,
				socialCode,
				profileMessage
			);
		}
	}
}
