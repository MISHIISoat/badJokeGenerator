package com.soat.badjokegenerator.infrastructure.entrypoints;

import com.soat.badjokegenerator.core.JokeToCreate;
import com.soat.badjokegenerator.usecase.BadJokeForbiddenException;
import com.soat.badjokegenerator.usecase.CreateBadJoke;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/bad-joke")
@RestController
public class BadJokeController {
    private final CreateBadJoke createBadJoke;

    public BadJokeController(CreateBadJoke createBadJoke) {
        this.createBadJoke = createBadJoke;
    }

    @PostMapping
    public ResponseEntity<URI> create(@RequestBody JokeToCreate jokeToCreate) throws BadJokeForbiddenException {
        var badJoke = createBadJoke.execute(jokeToCreate.getSentence(), jokeToCreate.getTags());
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(badJoke.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
