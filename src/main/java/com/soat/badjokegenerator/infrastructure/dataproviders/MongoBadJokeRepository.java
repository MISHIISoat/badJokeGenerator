package com.soat.badjokegenerator.infrastructure.dataproviders;

import com.mongodb.client.MongoDatabase;
import com.soat.badjokegenerator.core.BadJoke;
import com.soat.badjokegenerator.core.JokeToCreate;
import com.soat.badjokegenerator.infrastructure.utility.JsonMapper;
import com.soat.badjokegenerator.usecase.BadJokeRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MongoBadJokeRepository implements BadJokeRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MongoDatabase mongoDatabase;
    private final JsonMapper jsonMapper;
    private final ObjectIdGetter objectIdGetter;

    public MongoBadJokeRepository(MongoDatabase mongoDatabase, JsonMapper jsonMapper, ObjectIdGetter objectIdGetter) {
        this.mongoDatabase = mongoDatabase;
        this.jsonMapper = jsonMapper;
        this.objectIdGetter = objectIdGetter;
    }

    @Override
    public BadJoke create(JokeToCreate jokeToCreate) {
        var badJoke = mongoDatabase.getCollection("badJoke");
        var insertOneResult = badJoke.insertOne(Document.parse(jsonMapper.stringify(jokeToCreate)));
        var jokeUUID = objectIdGetter.get(insertOneResult).toString();

        log.info("bad joke created");
        return new BadJoke(jokeUUID, jokeToCreate.getSentence(), jokeToCreate.getTags(), true);
    }
}
