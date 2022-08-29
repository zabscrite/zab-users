package com.zab.zabusers.shared.common.api.request.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Password {

    String message() default "Password must be at least 8 alphanumeric characters long.";

    int minLength() default 8;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
