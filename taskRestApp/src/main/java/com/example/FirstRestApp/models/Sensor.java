package com.example.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Sensor")
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Имя сенсора должно быть от 3 до 30 символов!")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurement;

    public Sensor() {
    }

    public Sensor(String name, List<Measurement> measurement) {
        this.name = name;
        this.measurement = measurement;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        if (this.measurement == null) {
            this.measurement = new ArrayList<>();
        }
        this.measurement.add(measurement);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurement=" + measurement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return id == sensor.id && Objects.equals(name, sensor.name) && Objects.equals(measurement, sensor.measurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, measurement);
    }
}
