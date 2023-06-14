package com.example.FirstRestApp.controllers;

import com.example.FirstRestApp.dto.MeasurementsDTO;
import com.example.FirstRestApp.dto.SensorsDTO;
import com.example.FirstRestApp.models.Measurement;
import com.example.FirstRestApp.models.Sensor;
import com.example.FirstRestApp.services.MeasurementsService;
import com.example.FirstRestApp.services.SensorsService;
import com.example.FirstRestApp.util.measurementUtil.MeasurementErrorResponse;
import com.example.FirstRestApp.util.measurementUtil.MeasurementNotCreatedException;
import com.example.FirstRestApp.util.sensorsUtil.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper mapper;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper mapper, SensorValidator sensorValidator, SensorsService sensorsService) {
        this.measurementsService = measurementsService;
        this.mapper = mapper;
        this.sensorsService = sensorsService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                                     BindingResult bindingResult) {
        Sensor sensor = null;
        if (sensorsService.findByName(measurementsDTO.getSensor().getName()).isPresent()) {
            sensor = sensorsService.findByName(measurementsDTO.getSensor().getName()).get();
        } else {
            bindingResult.rejectValue("sensor", "", "Сенсор с таким именем не зарегестрирован!");
        }

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementNotCreatedException(errorMessage.toString());
        }
        Measurement measurement = convertToMeasurement(measurementsDTO);
        sensor.setMeasurement(measurement);
        measurement.setSensor(sensor);
        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementsDTO measurementsDTO) {
        return mapper.map(measurementsDTO, Measurement.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public List<MeasurementsDTO> getMeasurements() {
        return measurementsService.findAll().stream()
                .map(this::convertToMeasurementDto)
                .collect(Collectors.toList());
    }

    private MeasurementsDTO convertToMeasurementDto(Measurement measurement) {
        MeasurementsDTO measurementsDTO = mapper.map(measurement, MeasurementsDTO.class);
        measurementsDTO.setSensor(mapper.map(measurement.getSensor(), SensorsDTO.class));
        return measurementsDTO;
    }

    @GetMapping("/rainyDaysCount")
    public Integer rainyDaysCount() {
        return measurementsService.rainyDaysCount();
    }


}
