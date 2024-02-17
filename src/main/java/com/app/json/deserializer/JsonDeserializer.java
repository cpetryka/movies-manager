package com.app.json.deserializer;

public interface JsonDeserializer<T> {
    T fromJson(String filename);
}
