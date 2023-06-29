package com.soat.badjokegenerator.infrastructure.utility;


public interface JsonMapper {
    <T> T parse(String json, Class<T> aClass);

    <T> String stringify(T t);
}

