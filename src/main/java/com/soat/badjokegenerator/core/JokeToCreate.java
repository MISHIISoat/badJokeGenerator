package com.soat.badjokegenerator.core;

import com.soat.badjokegenerator.usecase.BadJokeForbiddenException;

import java.util.List;
import java.util.Objects;

public final class JokeToCreate {
    private final String sentence;
    private final List<String> tags;
    private final boolean isDraft;

    public JokeToCreate(String sentence, List<String> tags) throws BadJokeForbiddenException {
        if (sentence == null || sentence.isBlank())
            throw new BadJokeForbiddenException("Sentence should not be empty");
        this.sentence = sentence;
        this.tags = tags;
        this.isDraft = true;
    }

    public String getSentence() {
        return sentence;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean isDraft() {
        return isDraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JokeToCreate that = (JokeToCreate) o;
        return Objects.equals(sentence, that.sentence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentence);
    }

    @Override
    public String toString() {
        return "JokeToCreate{" +
                "sentence='" + sentence + '\'' +
                ", tags=" + tags +
                ", isDraft=" + isDraft +
                '}';
    }
}
