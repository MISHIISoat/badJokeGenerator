package com.soat.badjokegenerator.infrastructure.dataproviders;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public MongoDatabase mongoClient(@Value("${mongodb.uri}") String mongoUri) {
        log.info("Mongo client initialize");
        var mongoClient = MongoClients.create(mongoUri);
        return mongoClient.getDatabase("badjoke");
    }
}
