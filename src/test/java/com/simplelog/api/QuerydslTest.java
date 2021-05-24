package com.simplelog.api;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simplelog.api.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.simplelog.api.domain.QUser.user;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

    @Test
    void selectUserTest() {
        User user1 = new User("nickname1", "user1@gmail.com", "google", "example message1");
        User user2 = new User("nickname2", "user2@gmail.com", "naver", "example message2");
        User user3 = new User("nickname3", "user3@gmail.com", "google", "example message3");
        User user4 = new User("nickname4", "user4@gmail.com", "naver", "example message4");

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);

        List<User> users = queryFactory
                .selectFrom(user)
                .where(user.socialCode.eq("naver"))
                .fetch();

        assertThat(users)
                .extracting("nickname")
                .containsExactly("nickname2", "nickname4");
    }
}
