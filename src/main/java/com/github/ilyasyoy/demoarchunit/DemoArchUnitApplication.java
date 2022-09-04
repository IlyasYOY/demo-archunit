package com.github.ilyasyoy.demoarchunit;

import lombok.Value;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoArchUnitApplication {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DemoArchUnitApplication.class, args);
    }

    @Bean
    public Team team(
            ObjectProvider<Member> memberObjectProvider
    ) {
        Stream<Member> members = memberObjectProvider.orderedStream();
        return new Team(members.toList());
    }
}

@Value
class Team {
    List<Member> members;
}

@Value
class Member {
    String name;
}

class SomeImpl {

}