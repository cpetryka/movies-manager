package com.app.json.deserializer.impl;

import com.app.MoviesTestData;
import com.app.config.AppTestConfig;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.infrastructure.persistence.json.deserializer.JsonDeserializer;
import com.app.infrastructure.persistence.json.serializer.JsonSerializer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.app.MoviesTestData.MOVIES_DATA;
import static com.app.MoviesTestData.MOVIES_OUTPUT_FILENAME;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
class MoviesEntityJsonSerializerTest {
    @Autowired
    private JsonSerializer<MoviesEntity> jsonSerializer;

    @Test
    @DisplayName("when data is saved correctly")
    void test1() {
        jsonSerializer.toJson(MOVIES_DATA, MOVIES_OUTPUT_FILENAME);

        var path = Paths.get(MOVIES_OUTPUT_FILENAME);
        Assertions.assertTrue(Files.exists(path));
    }

    @AfterAll
    @SneakyThrows
    static void afterAll() {
        var path = Paths.get(MOVIES_OUTPUT_FILENAME);
        Files.delete(path);
    }
}
