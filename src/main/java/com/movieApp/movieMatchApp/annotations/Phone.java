package com.movieApp.movieMatchApp.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {

    String message() default "Invalid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 3;
    int max()default 20;
}
