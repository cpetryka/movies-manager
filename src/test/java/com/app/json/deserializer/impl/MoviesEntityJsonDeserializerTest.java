package com.app.json.deserializer.impl;

import com.app.config.AppTestConfig;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.infrastructure.persistence.json.deserializer.JsonDeserializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
public class MoviesEntityJsonDeserializerTest {
    @Autowired
    private JsonDeserializer<MoviesEntity> jsonDeserializer;

    @Test
    @DisplayName("when data is loaded correctly")
    void test1() {
        var path = Paths
                .get("src", "test", "resources", "movies-test.json")
                .toFile()
                .getAbsolutePath();
        var moviesData = jsonDeserializer.fromJson(path).movies();
        assertThat(moviesData).hasSize(2);
    }
}
