package com.soat.badjokegenerator.infrastructure.dataproviders;

import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonObjectId;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ObjectIdGetter {
    public ObjectId get(InsertOneResult insertOneResult) {
        return ((BsonObjectId) Objects.requireNonNull(insertOneResult.getInsertedId())).getValue();
    }
}
