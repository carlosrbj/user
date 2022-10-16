package com.hsob.user.model.address;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AddressValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AddressValidateInterface {
    String message() default "Address can not be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
