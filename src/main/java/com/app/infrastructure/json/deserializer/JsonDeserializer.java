package com.app.infrastructure.json.deserializer;

public interface JsonDeserializer<T> {
    T fromJson(String filename);
}
