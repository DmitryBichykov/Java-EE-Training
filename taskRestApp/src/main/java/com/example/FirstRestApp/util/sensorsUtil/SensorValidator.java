package com.example.FirstRestApp.util.sensorsUtil;

import com.example.FirstRestApp.dto.SensorsDTO;
import com.example.FirstRestApp.models.Sensor;
import com.example.FirstRestApp.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return SensorsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorsDTO sensor = (SensorsDTO) target;
        if (sensorsService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name","","Сенсор с такиме именем уже есть в БД!");
        }
    }
}
