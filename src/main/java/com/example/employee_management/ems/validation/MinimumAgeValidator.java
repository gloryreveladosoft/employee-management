package com.example.employee_management.ems.validation;

import java.time.LocalDate;
import java.time.Period;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinimumAgeValidator implements ConstraintValidator<MinimumAge, LocalDate>{

    private int minimumAge;

    @Override
    public void initialize(MinimumAge annotation) {
        minimumAge = annotation.value();
    }

    @Override
    public boolean isValid(
            LocalDate dateOfBirth,
            ConstraintValidatorContext context) {

        if (dateOfBirth == null) {
            return true;
        }

        if (dateOfBirth.isAfter(LocalDate.now())) {
            return false;
        }

        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        return age >= minimumAge;
    }

}
