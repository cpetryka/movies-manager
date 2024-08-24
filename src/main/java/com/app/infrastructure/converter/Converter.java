package com.app.infrastructure.converter;

public interface Converter<T, U> {
    U convert(T t);
}
