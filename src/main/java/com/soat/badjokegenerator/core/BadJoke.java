package com.soat.badjokegenerator.core;

import java.util.List;
import java.util.Objects;

public class BadJoke {
    private final String id;
    private final String sentence;
    private final List<String> tags;
    private final boolean isDraft;

    public BadJoke(String id, String sentence, List<String> tags, boolean isDraft) {
        this.id = id;
        this.sentence = sentence;
        this.tags = tags;
        this.isDraft = isDraft;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BadJoke badJoke = (BadJoke) o;
        return Objects.equals(id, badJoke.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
