package com.example.FIS_EmployerService.Config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployerIdValidate implements ConstraintValidator<ValidateEmployeeIdNullorEmpty,Integer> {
    @Override
    public boolean isValid(Integer employee_identification_number, ConstraintValidatorContext constraintValidatorContext) {
boolean flag =true;
        if(employee_identification_number ==0)
            flag=false;

        return flag;
    }
}
