package com.app.infrastructure.persistence.json.deserializer.impl;

import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.infrastructure.persistence.json.converter.JsonConverter;
import com.app.infrastructure.persistence.json.deserializer.generic.AbstractJsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class MoviesEntityJsonDeserializer extends AbstractJsonDeserializer<MoviesEntity> {
    public MoviesEntityJsonDeserializer(JsonConverter<MoviesEntity> converter) {
        super(converter);
    }
}
