package com.example.FIS_EmployerService.Config;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmployeeTypeValidator.class)
public @interface ValidationEmployerInfo {
    public String message() default "Employee Identification Number should be 9 character";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
