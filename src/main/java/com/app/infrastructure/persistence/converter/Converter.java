package com.app.infrastructure.persistence.converter;

public interface Converter<T, U> {
    U convert(T t);
}
