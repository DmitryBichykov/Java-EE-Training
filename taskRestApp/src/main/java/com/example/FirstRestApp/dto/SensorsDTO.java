package com.example.FirstRestApp.dto;

import com.example.FirstRestApp.models.Measurement;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

public class SensorsDTO {

    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Имя сенсора должно быть от 3 до 30 символов!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorsDTO that = (SensorsDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
