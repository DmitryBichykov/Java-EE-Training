package com.example.FirstRestApp.dto;

import com.example.FirstRestApp.models.Sensor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public class MeasurementsDTO {

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
    private SensorsDTO sensors;

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

    public void setTimeMeasurement(LocalDateTime timeMeasurement) {
        this.timeMeasurement = timeMeasurement;
    }

    public SensorsDTO getSensor() {
        return sensors;
    }

    public void setSensor(SensorsDTO sensors) {
        this.sensors = sensors;
    }
}
