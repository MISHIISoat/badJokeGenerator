package com.soat.badjokegenerator.infrastructure.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonMapperImpl implements JsonMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T parse(String json, Class<T> aClass) {
        try {
            return objectMapper.readValue(json, aClass);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException(e);
        }
    }

    @Override
    public <T> String stringify(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException(e);
        }
    }
}
