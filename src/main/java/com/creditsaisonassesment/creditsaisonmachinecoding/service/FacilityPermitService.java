package com.creditsaisonassesment.creditsaisonmachinecoding.service;

import static com.creditsaisonassesment.creditsaisonmachinecoding.helper.common.Constants.EXPIRATION_DATETIME_FORMATTER;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.creditsaisonassesment.creditsaisonmachinecoding.entities.FacilityPermit;
import com.creditsaisonassesment.creditsaisonmachinecoding.entities.enums.PermitStatus;
import com.creditsaisonassesment.creditsaisonmachinecoding.entities.request.FacilityPermitRequest;
import com.creditsaisonassesment.creditsaisonmachinecoding.exception.FacilityPermitException;
import com.creditsaisonassesment.creditsaisonmachinecoding.helper.service.CSVService;
import com.creditsaisonassesment.creditsaisonmachinecoding.repository.FacilityPermitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The type Facility permit service.
 */
@Slf4j
@Service
public class FacilityPermitService {

    @Autowired
    private FacilityPermitRepository facilityPermitRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CSVService csvService;

    private void validateDatabase() {
        if (facilityPermitRepository.findAll(Pageable.ofSize(1)).isEmpty()) {
            log.info("Re-initialising the database from csv file");
            csvService.saveListOfPermitsFromCSVToDB(new File(System.getProperty("user.dir") + "/src/main/resources/static/Mobile_Food_Facility_Permit.csv"));
        }
    }

    /**
     * Gets facility permit by location id.
     *
     * @param locationId the location id
     * @return the facility permit by location id
     */
    public FacilityPermit getFacilityPermitByLocationId(String locationId) {
        validateDatabase();
        AtomicReference<FacilityPermit> facilityPermitAtomicReference = new AtomicReference<>();
        facilityPermitRepository.findById(Long.parseLong(locationId)).ifPresentOrElse(facilityPermit -> {
            facilityPermitAtomicReference.set(facilityPermit);
        }, () -> {
            throw FacilityPermitException.throwLocationIdNotFoundException();
        });
        var response = facilityPermitAtomicReference.get();
        log.info("RESPONSE getFacilityPermitByLocationId {}", response);
        return response;
    }

    /**
     * Gets facility permit by applicant name.
     *
     * @param applicantName the applicant name
     * @return the facility permit by applicant name
     */
    public List<FacilityPermit> getFacilityPermitByApplicantName(String applicantName) {
        validateDatabase();
        var response = facilityPermitRepository.findAllByApplicant(applicantName).get();
        log.info("RESPONSE getFacilityPermitByApplicantName {}", response);
        return response;
    }

    /**
     * Gets facility permit by street name.
     *
     * @param streetName the street name
     * @return the facility permit by street name
     */
    public List<FacilityPermit> getFacilityPermitByStreetName(String streetName) {
        validateDatabase();
        var response = facilityPermitRepository.findAllByLocationDescription(streetName).get();
        log.info("RESPONSE getFacilityPermitByStreetName {}", response);
        return response;
    }

    /**
     * Create facility permit facility permit.
     *
     * @param facilityPermitRequest the facility permit request
     * @return the facility permit
     */
    public FacilityPermit createFacilityPermit(FacilityPermitRequest facilityPermitRequest) {
        validateDatabase();
        FacilityPermit facilityPermit = objectMapper.convertValue(facilityPermitRequest, FacilityPermit.class);
        var response = facilityPermitRepository.save(facilityPermit);
        log.info("RESPONSE createFacilityPermit {}", response);
        return response;
    }

    /**
     * Gets expired facility permits.
     *
     * @param expirationDate the expiration date
     * @return the expired facility permits
     */
    public List<FacilityPermit> getExpiredFacilityPermits(String expirationDate) {
        validateDatabase();
        List<FacilityPermit> response = new ArrayList<>();
        if(expirationDate == null) {
            facilityPermitRepository.findAllByStatus(PermitStatus.EXPIRED).ifPresent(response::addAll);
        } else {
            String parsedDate = LocalDateTime.parse(expirationDate, EXPIRATION_DATETIME_FORMATTER).format(EXPIRATION_DATETIME_FORMATTER);
            facilityPermitRepository.findAllByStatusAndExpirationDateLessThanEqual(PermitStatus.EXPIRED, parsedDate)
                .ifPresent(response::addAll);
        }
        log.info("RESPONSE getExpiredFacilityPermits {}", response);
        return response;
    }

    /**
     * Gets closest truck from location.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the closest truck from location
     */
    public FacilityPermit getClosestTruckFromLocation(String latitude, String longitude) {
        validateDatabase();
        AtomicReference<Double> minDistanceSoFar = new AtomicReference<>(Double.MAX_VALUE);
        AtomicReference<FacilityPermit> nearestFacility = new AtomicReference<>();
        facilityPermitRepository.findAll().forEach(permit -> {
            double distBetweenCurrentFacilityAndInput = findDistanceBetweenInKM(Double.parseDouble(latitude),
                Double.parseDouble(longitude),
                Double.parseDouble(permit.getLatitude()),
                Double.parseDouble(permit.getLongitude()));
            if (distBetweenCurrentFacilityAndInput != 0 && minDistanceSoFar.get() > distBetweenCurrentFacilityAndInput) {
                minDistanceSoFar.set(distBetweenCurrentFacilityAndInput);
                nearestFacility.set(permit);
            }
        });
        log.info("RESPONSE nearest truck is {} km, details: {}", minDistanceSoFar.get(), nearestFacility.get());
        return nearestFacility.get();
    }

    private double findDistanceBetweenInKM(double lat1, double lon1, double lat2, double lon2) {
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
            + Math.cos(lat1) * Math.cos(lat2)
            * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return(c * r);
    }

    /**
     * Gets all permits.
     *
     * @param size the size
     * @return the all permits
     */
    public List<FacilityPermit> getAllPermits(Integer size) {
        validateDatabase();
        List<FacilityPermit> allPermits = facilityPermitRepository.findAll();
        allPermits.subList(0, Math.min(size, allPermits.size()));
        log.info("RESPONSE getAllPermits {}", allPermits);
        return allPermits;
    }
}
