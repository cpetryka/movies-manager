package com.app.json.deserializer.impl;

import com.app.json.converter.JsonConverter;
import com.app.json.deserializer.generic.AbstractJsonDeserializer;
import com.app.json.model.MoviesData;
import org.springframework.stereotype.Component;

@Component
public class MoviesDataJsonDeserializer extends AbstractJsonDeserializer<MoviesData> {
    public MoviesDataJsonDeserializer(JsonConverter<MoviesData> converter) {
        super(converter);
    }
}
