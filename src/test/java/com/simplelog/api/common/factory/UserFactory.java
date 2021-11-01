package com.simplelog.api.common.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import com.simplelog.api.domain.User;
import com.simplelog.api.repository.UserRepository;

@TestComponent
public class UserFactory {

	@Autowired
	private UserRepository userRepository;

	public User user() {
		User user = createUser();
		return save(user);
	}

	public User user(String nickname) {
		User user = createUser(nickname);
		return save(user);
	}

	public User user(Long id, String nickname) {
		User user = createUser(id, nickname);
		return save(user);
	}

	private User save(User user) {
		return userRepository.save(user);
	}

	private static User createUser() {
		return MockUser.builder().build();
	}

	private static User createUser(String nickname) {
		return MockUser
			.builder()
			.nickname(nickname)
			.build();
	}

	private static User createUser(Long id, String nickname) {
		return MockUser
			.builder()
			.id(id)
			.nickname(nickname)
			.build();
	}
}
