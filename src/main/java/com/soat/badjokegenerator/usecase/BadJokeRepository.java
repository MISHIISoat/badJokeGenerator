package com.soat.badjokegenerator.usecase;

import com.soat.badjokegenerator.core.BadJoke;
import com.soat.badjokegenerator.core.JokeToCreate;

public interface BadJokeRepository {
    BadJoke create(JokeToCreate draftJokeToSave);
}
