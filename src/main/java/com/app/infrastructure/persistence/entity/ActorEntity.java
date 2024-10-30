package com.app.infrastructure.persistence.entity;

import com.app.domain.movies_management.model.Actor;
import com.app.domain.movies_management.model.type.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
public record ActorEntity(String name, String gender, LocalDate birthDate, LocalDate deathDate, String biography) {
    public Actor toActor() {
        return Actor
                .builder()
                .name(name)
                .gender(Gender.fromValue(gender))
                .birthDate(birthDate)
                .deathDate(deathDate)
                .biography(biography)
                .build();
    }
}
