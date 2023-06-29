package com.soat.badjokegenerator.usecase;

import com.soat.badjokegenerator.core.BadJoke;
import com.soat.badjokegenerator.core.JokeToCreate;

import java.util.List;

public class CreateBadJoke {
    private final BadJokeRepository draftJokeRepository;

    public CreateBadJoke(BadJokeRepository draftJokeRepository) {

        this.draftJokeRepository = draftJokeRepository;
    }

    public BadJoke execute(String sentence, List<String> tags) throws BadJokeForbiddenException {
        return draftJokeRepository.create(new JokeToCreate(sentence, tags));
    }
}
