package com.soat.badjokegenerator.infrastructure.entrypoints;

import com.soat.badjokegenerator.usecase.BadJokeRepository;
import com.soat.badjokegenerator.usecase.CreateBadJoke;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfiguration {
    @Bean
    public CreateBadJoke createDraftJoke(BadJokeRepository badJokeRepository) {
        return new CreateBadJoke(badJokeRepository);
    }
}
