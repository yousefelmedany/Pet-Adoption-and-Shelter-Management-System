package com.example.pets.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ApplicationId implements Serializable {
    private Long adopter;
    private Long pet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationId applicationId= (ApplicationId) o;
        return Objects.equals(adopter, applicationId.adopter) &&
                Objects.equals(pet, applicationId.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adopter, pet);
    }
}
