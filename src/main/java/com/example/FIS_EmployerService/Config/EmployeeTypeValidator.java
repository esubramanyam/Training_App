package com.example.FIS_EmployerService.Config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeTypeValidator implements ConstraintValidator <ValidationEmployerInfo,Integer>{

    @Override
    public boolean isValid(Integer employee_identification_number, ConstraintValidatorContext constraintValidatorContext) {


        return String.valueOf(employee_identification_number).matches("\\d{9}");
    }
}
