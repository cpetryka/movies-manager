package com.app.infrastructure.persistence.json.serializer.generic;

import com.app.infrastructure.persistence.json.converter.JsonConverter;
import com.app.infrastructure.persistence.json.serializer.JsonSerializer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.FileWriter;

@RequiredArgsConstructor
public abstract class AbstractJsonSerializer<T> implements JsonSerializer<T> {
    private final JsonConverter<T> converter;

    @SneakyThrows
    @Override
    public void toJson(T data, String filename) {
        try (var writer = new FileWriter(filename)) {
            converter.toJson(data, writer);
        }
    }
}
