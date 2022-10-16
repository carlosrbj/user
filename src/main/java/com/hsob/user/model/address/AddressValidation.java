package com.hsob.user.model.address;

import com.hsob.user.model.user.AddressRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.http.HttpStatus.NO_CONTENT;

public class AddressValidation implements ConstraintValidator<AddressValidateInterface, AddressRequest> {

    @Override
    public void initialize(AddressValidateInterface constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AddressRequest value, ConstraintValidatorContext context) {
        if (value == null) throw new ResponseStatusException(NO_CONTENT,"Addres can not be empty");
        if (value.getCity().isEmpty()) throw new ResponseStatusException(NO_CONTENT,"City can not be empty");
        if (value.getCountry().isEmpty()) throw new ResponseStatusException(NO_CONTENT,"Country can not be empty");
        if (value.getState().isEmpty()) throw new ResponseStatusException(NO_CONTENT,"State can not be empty");
        if (value.getZipcode().isEmpty()) throw new ResponseStatusException(NO_CONTENT,"Zipcode can not be empty");
        if (value.getStreet().isEmpty()) throw new ResponseStatusException(NO_CONTENT,"Street can not be empty");
        if (value.getNumber().isEmpty()) throw new ResponseStatusException(NO_CONTENT,"Number can not be empty");
        return true;
    }
}
