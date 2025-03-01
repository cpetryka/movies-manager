package com.app.infrastructure.persistence.json.deserializer;

public interface JsonDeserializer<T> {
    T fromJson(String filename);
}
