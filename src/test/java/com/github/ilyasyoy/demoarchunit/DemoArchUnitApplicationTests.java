package com.github.ilyasyoy.demoarchunit;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class DemoArchUnitApplicationTests {

    @Test
    void contextLoads(@Autowired ApplicationContext applicationContext) {
        Assertions.assertThat(applicationContext).isNotNull();
    }

    @Test
    void testContainsTeam(@Autowired ApplicationContext applicationContext) {
        Team team = assertDoesNotThrow(() -> applicationContext.getBean("team", Team.class));

        assertAll(
                () -> assertNotNull(team),
                () -> assertAll(
                        () -> assertNotNull(team.getMembers()),
                        () -> assertFalse(team.getMembers().isEmpty()),
                        () -> assertEquals(2, team.getMembers().size()),
                        () -> assertAll(
                                () -> assertEquals(team.getMembers().get(0).getName(), "Ivan"),
                                () -> assertEquals(team.getMembers().get(1).getName(), "Ilia")
                        )
                )
        );
    }

    @TestConfiguration
    static class Config {

        @Bean
        @Order(0)
        public Member ivan() {
            return new Member("Ivan");
        }

        @Bean
        @Order(1)
        public Member ilia() {
            return new Member("Ilia");
        }
    }
}
