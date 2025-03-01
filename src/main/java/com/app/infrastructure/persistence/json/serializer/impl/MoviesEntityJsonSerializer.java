package com.app.infrastructure.persistence.json.serializer.impl;

import com.app.infrastructure.persistence.entity.MovieEntity;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.infrastructure.persistence.json.converter.JsonConverter;
import com.app.infrastructure.persistence.json.serializer.generic.AbstractJsonSerializer;
import org.springframework.stereotype.Component;

@Component
public class MoviesEntityJsonSerializer extends AbstractJsonSerializer<MoviesEntity> {
    public MoviesEntityJsonSerializer(JsonConverter<MoviesEntity> converter) {
        super(converter);
    }
}
