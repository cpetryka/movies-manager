package com.app.infrastructure.json.deserializer.impl;

import com.app.infrastructure.json.converter.JsonConverter;
import com.app.infrastructure.json.deserializer.generic.AbstractJsonDeserializer;
import com.app.infrastructure.json.model.MoviesData;
import org.springframework.stereotype.Component;

@Component
public class MoviesDataJsonDeserializer extends AbstractJsonDeserializer<MoviesData> {
    public MoviesDataJsonDeserializer(JsonConverter<MoviesData> converter) {
        super(converter);
    }
}
