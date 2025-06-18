package com.example.FIS_EmployerService.Config;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmployerIdValidate.class)
public @interface ValidateEmployeeIdNullorEmpty {

    public String message() default "Employee Identification Number should not be empty";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
