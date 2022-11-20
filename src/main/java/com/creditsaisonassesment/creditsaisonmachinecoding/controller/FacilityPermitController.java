package com.creditsaisonassesment.creditsaisonmachinecoding.controller;

import java.util.List;

import com.creditsaisonassesment.creditsaisonmachinecoding.entities.FacilityPermit;
import com.creditsaisonassesment.creditsaisonmachinecoding.entities.request.FacilityPermitRequest;
import com.creditsaisonassesment.creditsaisonmachinecoding.entities.response.FacilityPermitResponse;
import com.creditsaisonassesment.creditsaisonmachinecoding.service.FacilityPermitService;
import com.creditsaisonassesment.creditsaisonmachinecoding.validator.FacilityPermitRequestValidator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Facility permit controller.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/permit")
public class FacilityPermitController {

    @Autowired
    private FacilityPermitService facilityPermitService;

    @Autowired
    private FacilityPermitRequestValidator validator;

    /**
     * Gets all permits.
     *
     * @param size the size
     * @return the all permits
     */
    @GetMapping("/all")
    public ResponseEntity<List<FacilityPermit>> getAllPermits(@RequestParam Integer size) {
        log.info("GET all permits in size: {}", size);
        return ResponseEntity.status(HttpStatus.FOUND).body(facilityPermitService.getAllPermits(size));
    }

    /**
     * Gets facility permit by location id.
     *
     * @param locationId the location id
     * @return the facility permit by location id
     */
    @GetMapping("/location")
    public ResponseEntity<FacilityPermit> getFacilityPermitByLocationId(@RequestParam String locationId) {
        log.info("GET location by locationId: {}", locationId);
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(facilityPermitService.getFacilityPermitByLocationId(locationId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Get facility permit by applicant name response entity.
     *
     * @param applicantName the applicant name
     * @return the response entity
     */
    @GetMapping("/applicant")
    public ResponseEntity<List<FacilityPermit>> getFacilityPermitByApplicantName(@RequestParam String applicantName){
        log.info("GET all applicants by applicant name: {}", applicantName);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facilityPermitService.getFacilityPermitByApplicantName(applicantName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Get facility permit by street name response entity.
     *
     * @param streetName the street name
     * @return the response entity
     */
    @GetMapping("/street")
    public ResponseEntity<List<FacilityPermit>> getFacilityPermitByStreetName(@RequestParam String streetName){
        log.info("GET all permits of street: {}", streetName);
        return ResponseEntity.status(HttpStatus.OK).body(facilityPermitService.getFacilityPermitByStreetName(streetName));
    }

    /**
     * Get closest truck from location response entity.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the response entity
     */
    @GetMapping("/closest-truck")
    public ResponseEntity<FacilityPermit> getClosestTruckFromLocation(@RequestParam String latitude, @RequestParam String longitude){
        log.info("GET closest food-truck from location: ({}, {})", latitude, longitude);
        return ResponseEntity.status(HttpStatus.OK).body(facilityPermitService.getClosestTruckFromLocation(latitude, longitude));
    }

    /**
     * Gets facility permit by expiration date.
     *
     * @param expirationDate the expiration date (optional field), by default returns all permits with state EXPIRED
     * @return the facility permit by expiration date
     */
    @GetMapping("/expired-permits")
    public ResponseEntity<List<FacilityPermit>> getFacilityPermitByExpirationDate(@RequestParam(required = false) String expirationDate){
        log.info("GET all permits that are expired, expirationDate(optional): {}", expirationDate);
        return ResponseEntity.status(HttpStatus.OK).body(facilityPermitService.getExpiredFacilityPermits(expirationDate));
    }

    /**
     * Create facility permit response entity.
     *
     * @param facilityPermitRequest the facility permit request
     * @param errors                the errors
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Object> createFacilityPermit(@RequestBody FacilityPermitRequest facilityPermitRequest, BindingResult errors) {
        log.info("POST create new permit request: {}", facilityPermitRequest);
        validator.validate(facilityPermitRequest, errors);
        return ResponseEntity.status(HttpStatus.CREATED).body(facilityPermitService.createFacilityPermit(facilityPermitRequest));
    }
}
