package com.movieApp.movieMatchApp.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    /**
     *Mobile may contain digits and following characters.
     * '+'
     * '-'
     * '.'
     * 'space'
     */

    private int min;
    private int max;

    @Override
    public void initialize(Phone phone) {
        this.min = phone.min();
        this.max = phone.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
