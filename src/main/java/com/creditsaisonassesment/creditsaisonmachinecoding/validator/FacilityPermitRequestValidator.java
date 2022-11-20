package com.creditsaisonassesment.creditsaisonmachinecoding.validator;

import com.creditsaisonassesment.creditsaisonmachinecoding.entities.request.FacilityPermitRequest;
import com.creditsaisonassesment.creditsaisonmachinecoding.exception.FacilityPermitException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.ValidationUtils;

/**
 * The type Facility permit request validator.
 */
@Slf4j
@Component
public class FacilityPermitRequestValidator implements SmartValidator {
    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FacilityPermitRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("Validating request to create new permit");
        FacilityPermitRequest facilityPermitRequest = (FacilityPermitRequest) target;
        ValidationUtils.rejectIfEmpty(errors, "locationId", "locationId cannot be null");
        ValidationUtils.rejectIfEmpty(errors, "permit", "permit cannot be null");

        if(errors.hasErrors()){
            log.error("Error while validating request: {}", errors.getAllErrors());
            throw FacilityPermitException.throwValidationException(errors.getAllErrors().toString());
        }
    }
}
