package com.simplelog.api.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.simplelog.api.common.factory.PostFactory;
import com.simplelog.api.common.factory.UserFactory;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Import({
	UserFactory.class,
	PostFactory.class
})
public class IntegrationTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected UserFactory userFactory;

	@Autowired
	protected PostFactory postFactory;

}

