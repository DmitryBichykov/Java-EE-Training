package com.example.FirstRestApp.controllers;

import com.example.FirstRestApp.dto.SensorsDTO;
import com.example.FirstRestApp.models.Sensor;
import com.example.FirstRestApp.services.SensorsService;
import com.example.FirstRestApp.util.sensorsUtil.SensorErrorResponse;
import com.example.FirstRestApp.util.sensorsUtil.SensorNotCreatedException;
import com.example.FirstRestApp.util.sensorsUtil.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;
    private final ModelMapper mapper;
    private final SensorValidator sensorValidator;


    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper mapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.mapper = mapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorsDTO sensorsDTO,
                                             BindingResult bindingResult) {

        sensorValidator.validate(sensorsDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        }
        sensorsService.save(convertToService(sensorsDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToService(SensorsDTO sensorsDTO) {
        return mapper.map(sensorsDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private SensorsDTO convertToPersonDto(Sensor sensor) {
        return mapper.map(sensor, SensorsDTO.class);
    }

}
