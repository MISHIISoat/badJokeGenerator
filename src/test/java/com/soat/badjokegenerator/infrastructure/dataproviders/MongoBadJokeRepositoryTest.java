package com.soat.badjokegenerator.infrastructure.dataproviders;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.soat.badjokegenerator.core.BadJoke;
import com.soat.badjokegenerator.core.JokeToCreate;
import com.soat.badjokegenerator.infrastructure.utility.JsonMapper;
import com.soat.badjokegenerator.infrastructure.utility.JsonMapperImpl;
import com.soat.badjokegenerator.usecase.BadJokeForbiddenException;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MongoBadJokeRepositoryTest {
    @Mock
    private MongoDatabase mockMongoDatabase;
    @Mock
    private MongoCollection<Document> mockMongoCollection;
    @Mock
    private InsertOneResult mockInsertOneResult;

    @Mock
    ObjectIdGetter mockObjectIdGetter;

    private final JsonMapper jsonMapper = new JsonMapperImpl();

    MongoBadJokeRepository mongoDraftJokeRepository;

    @BeforeEach
    void setup() {
        mongoDraftJokeRepository = new MongoBadJokeRepository(mockMongoDatabase, jsonMapper, mockObjectIdGetter);
    }

    @Nested
    class CreateBadJoke {

        @Test
        void should_insert_joke_and_return_bad_joke_with_new_uuid() throws BadJokeForbiddenException {
            var input = new JokeToCreate("a joke", List.of("toto"));
            when(mockMongoDatabase.getCollection("badJoke")).thenReturn(mockMongoCollection);
            when(mockMongoCollection.insertOne(Document.parse(jsonMapper.stringify(input)))).thenReturn(mockInsertOneResult);
            var objectId = new ObjectId(new Date());
            when(mockObjectIdGetter.get(mockInsertOneResult)).thenReturn(objectId);

            var result = mongoDraftJokeRepository.create(input);

            var expected = new BadJoke(objectId.toString(), "a joke", List.of("toto"), true);
            assertThat(result).isEqualTo(expected);
        }

    }
}