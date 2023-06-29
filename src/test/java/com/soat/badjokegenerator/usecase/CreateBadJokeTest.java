package com.soat.badjokegenerator.usecase;

import com.soat.badjokegenerator.core.BadJoke;
import com.soat.badjokegenerator.core.JokeToCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateBadJokeTest {
    @Mock
    BadJokeRepository draftJokeRepository;
    private CreateBadJoke createDraftJoke;
    private final String sentence = "sentence content";

    @BeforeEach
    void setup() {
        createDraftJoke = new CreateBadJoke(draftJokeRepository);
    }

    @Test
    void should_call_repository_to_create_draft() throws BadJokeForbiddenException {
        JokeToCreate draftJokeToSave = new JokeToCreate(sentence, List.of());

        createDraftJoke.execute(sentence, List.of());

        verify(draftJokeRepository, times(1)).create(draftJokeToSave);
    }

    @Test
    void should_return_draftJoke() throws BadJokeForbiddenException {
        JokeToCreate draftJokeToSave = new JokeToCreate(sentence, List.of());
        var draftJoke = new BadJoke("newJokeId", sentence, List.of(), true);
        when(draftJokeRepository.create(draftJokeToSave)).thenReturn(draftJoke);

        var result = createDraftJoke.execute(sentence, List.of());

        assertThat(result).isEqualTo(draftJoke);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void given_no_sentence_is_forbidden(String nullOrEmptySentence) {
        var throwable = catchThrowable(() -> createDraftJoke.execute(nullOrEmptySentence, List.of()));

        assertThat(throwable).isNotNull();
        assertThat(throwable.getClass()).isEqualTo(BadJokeForbiddenException.class);
        assertThat(throwable.getMessage()).isEqualTo("Sentence should not be empty");
    }
}