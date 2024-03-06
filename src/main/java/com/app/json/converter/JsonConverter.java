package com.app.json.converter;

import java.io.FileReader;
import java.io.FileWriter;

public interface JsonConverter<T> {
    T fromJson(FileReader reader, Class<T> tClass);
}
