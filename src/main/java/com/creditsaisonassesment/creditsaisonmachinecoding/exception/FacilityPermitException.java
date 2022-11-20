package com.creditsaisonassesment.creditsaisonmachinecoding.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * The type Facility permit exception.
 */
@ControllerAdvice
public class FacilityPermitException extends RuntimeException {

    /**
     * Instantiates a new Facility permit exception.
     */
    FacilityPermitException() {
        super();
    }

    /**
     * Instantiates a new Facility permit exception.
     *
     * @param message the message
     */
    FacilityPermitException(String message) {
        super(message);
    }

    /**
     * Throw location id not found exception facility permit exception.
     *
     * @return the facility permit exception
     */
    public static FacilityPermitException throwLocationIdNotFoundException() {
        return new FacilityPermitException();
    }

    /**
     * Throw validation exception facility permit exception.
     *
     * @param message the message
     * @return the facility permit exception
     */
    public static FacilityPermitException throwValidationException(String message) {
        return new FacilityPermitException(message);
    }
}
