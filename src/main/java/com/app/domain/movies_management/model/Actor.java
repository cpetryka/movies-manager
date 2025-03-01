package com.app.domain.movies_management.model;

import com.app.domain.movies_management.model.type.Gender;
import com.app.infrastructure.persistence.entity.ActorEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Comparator;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Actor {
    final String name;
    final Gender gender;
    final LocalDate birthDate;
    final LocalDate deathDate;
    final String biography;

    public String getFullName() {
        return name;
    }

    public int calculateAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public boolean isAlive() {
        return deathDate == null;
    }

    public ActorEntity toActorEntity() {
        return ActorEntity
                .builder()
                .name(name)
                .gender(gender.name())
                .birthDate(birthDate)
                .deathDate(deathDate)
                .biography(biography)
                .build();
    }
}
