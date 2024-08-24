package com.app.infrastructure.json.converter.impl;

import com.app.infrastructure.json.converter.JsonConverter;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileReader;

@Component
@RequiredArgsConstructor
public class GsonConverter<T> implements JsonConverter<T> {
    private final Gson gson;

    @Override
    public T fromJson(FileReader reader, Class<T> tClass) {
        return gson.fromJson(reader, tClass);
    }
}
