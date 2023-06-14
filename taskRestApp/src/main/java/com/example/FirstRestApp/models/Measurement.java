package com.example.FirstRestApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Min(value = -100, message = "Значение должно быть больше -100!")
    @Max(value = 100, message = "Значение должно быть меньше 100!")
    private double value;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "time_measurement")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeMeasurement;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @JsonIgnore
    private Sensor sensor;

    public Measurement() {
    }

    public Measurement(double value, boolean raining, LocalDateTime timeMeasurement, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.timeMeasurement = timeMeasurement;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getTimeMeasurement() {
        return timeMeasurement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeMeasurement(LocalDateTime timeMeasurement) {
        this.timeMeasurement = timeMeasurement;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                ", value=" + value +
                ", raining=" + raining +
                ", timeMeasurement=" + timeMeasurement +
                ", sensor=" + sensor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return id == that.id && Double.compare(that.value, value) == 0 && raining == that.raining && Objects.equals(timeMeasurement, that.timeMeasurement) && Objects.equals(sensor, that.sensor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, raining, timeMeasurement, sensor);
    }
}
