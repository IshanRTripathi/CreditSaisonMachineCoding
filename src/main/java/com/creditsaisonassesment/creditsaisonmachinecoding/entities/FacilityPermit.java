package com.creditsaisonassesment.creditsaisonmachinecoding.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.creditsaisonassesment.creditsaisonmachinecoding.entities.enums.PermitStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class FacilityPermit {
    @Id
    private Long locationId;

    private String applicant;
    private String facilityType;
    private String cnn;
    private String locationDescription;
    private String address;
    private String blockLot;
    private String block;
    private String lot;
    private String permit;

    @Enumerated
    private PermitStatus status;

    @Column(length = 512)
    private String foodItems;
    private String x;
    private String y;
    private String latitude;
    private String longitude;
    private String schedule;
    private String daysHours;
    private String NOISent;
    private String approved;
    private String received;
    private String priorPermit;
    private String expirationDate;
    private String location;
    private String firePreventionDistricts;
    private String policeDistricts;
    private String supervisorDistricts;
    private String zipCodes;
    private String neighborhoods;

    /**
     * Sets location id.
     *
     * @param locationId the location id
     * @return the FacilityPermit
     */
    public FacilityPermit setLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    /**
     * Sets applicant.
     *
     * @param applicant the applicant
     * @return the FacilityPermit
     */
    public FacilityPermit setApplicant(String applicant) {
        this.applicant = applicant;
        return this;
    }

    /**
     * Sets facility type.
     *
     * @param facilityType the facility type
     * @return the FacilityPermit
     */
    public FacilityPermit setFacilityType(String facilityType) {
        this.facilityType = facilityType;
        return this;
    }

    /**
     * Sets cnn.
     *
     * @param cnn the cnn
     * @return the FacilityPermit
     */
    public FacilityPermit setCnn(String cnn) {
        this.cnn = cnn;
        return this;
    }

    /**
     * Sets location description.
     *
     * @param locationDescription the location description
     * @return the FacilityPermit
     */
    public FacilityPermit setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
        return this;
    }

    /**
     * Sets address.
     *
     * @param address the address
     * @return the FacilityPermit
     */
    public FacilityPermit setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Sets block lot.
     *
     * @param blockLot the block lot
     * @return the FacilityPermit
     */
    public FacilityPermit setBlockLot(String blockLot) {
        this.blockLot = blockLot;
        return this;
    }

    /**
     * Sets block.
     *
     * @param block the block
     * @return the FacilityPermit
     */
    public FacilityPermit setBlock(String block) {
        this.block = block;
        return this;
    }

    /**
     * Sets lot.
     *
     * @param lot the lot
     * @return the FacilityPermit
     */
    public FacilityPermit setLot(String lot) {
        this.lot = lot;
        return this;
    }

    /**
     * Sets permit.
     *
     * @param permit the permit
     * @return the FacilityPermit
     */
    public FacilityPermit setPermit(String permit) {
        this.permit = permit;
        return this;
    }

    /**
     * Sets status.
     *
     * @param status the status
     * @return the FacilityPermit
     */
    public FacilityPermit setStatus(PermitStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Sets food items.
     *
     * @param foodItems the food items
     * @return the FacilityPermit
     */
    public FacilityPermit setFoodItems(String foodItems) {
        this.foodItems = foodItems;
        return this;
    }

    /**
     * Sets x.
     *
     * @param x the x
     * @return the FacilityPermit
     */
    public FacilityPermit setX(String x) {
        this.x = x;
        return this;
    }

    /**
     * Sets y.
     *
     * @param y the y
     * @return the FacilityPermit
     */
    public FacilityPermit setY(String y) {
        this.y = y;
        return this;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     * @return the FacilityPermit
     */
    public FacilityPermit setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     * @return the FacilityPermit
     */
    public FacilityPermit setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * Sets schedule.
     *
     * @param schedule the schedule
     * @return the FacilityPermit
     */
    public FacilityPermit setSchedule(String schedule) {
        this.schedule = schedule;
        return this;
    }

    /**
     * Sets days hours.
     *
     * @param daysHours the days hours
     * @return the FacilityPermit
     */
    public FacilityPermit setDaysHours(String daysHours) {
        this.daysHours = daysHours;
        return this;
    }

    /**
     * Sets noi sent.
     *
     * @param NOISent the noi sent
     * @return the FacilityPermit
     */
    public FacilityPermit setNOISent(String NOISent) {
        this.NOISent = NOISent;
        return this;
    }

    /**
     * Sets approved.
     *
     * @param approved the approved
     * @return the FacilityPermit
     */
    public FacilityPermit setApproved(String approved) {
        this.approved = approved;
        return this;
    }

    /**
     * Sets received.
     *
     * @param received the received
     * @return the FacilityPermit
     */
    public FacilityPermit setReceived(String received) {
        this.received = received;
        return this;
    }

    /**
     * Sets prior permit.
     *
     * @param priorPermit the prior permit
     * @return the FacilityPermit
     */
    public FacilityPermit setPriorPermit(String priorPermit) {
        this.priorPermit = priorPermit;
        return this;
    }

    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     * @return the FacilityPermit
     */
    public FacilityPermit setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    /**
     * Sets location.
     *
     * @param location the location
     * @return the FacilityPermit
     */
    public FacilityPermit setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Sets fire prevention districts.
     *
     * @param firePreventionDistricts the fire prevention districts
     * @return the FacilityPermit
     */
    public FacilityPermit setFirePreventionDistricts(String firePreventionDistricts) {
        this.firePreventionDistricts = firePreventionDistricts;
        return this;
    }

    /**
     * Sets police districts.
     *
     * @param policeDistricts the police districts
     * @return the FacilityPermit
     */
    public FacilityPermit setPoliceDistricts(String policeDistricts) {
        this.policeDistricts = policeDistricts;
        return this;
    }

    /**
     * Sets supervisor districts.
     *
     * @param supervisorDistricts the supervisor districts
     * @return the FacilityPermit
     */
    public FacilityPermit setSupervisorDistricts(String supervisorDistricts) {
        this.supervisorDistricts = supervisorDistricts;
        return this;
    }

    /**
     * Sets zip codes.
     *
     * @param zipCodes the zip codes
     * @return the FacilityPermit
     */
    public FacilityPermit setZipCodes(String zipCodes) {
        this.zipCodes = zipCodes;
        return this;
    }

    /**
     * Sets neighborhoods.
     *
     * @param neighborhoods the neighborhoods
     * @return the FacilityPermit
     */
    public FacilityPermit setNeighborhoods(String neighborhoods) {
        this.neighborhoods = neighborhoods;
        return this;
    }

    /**
     * Instantiates a new Facility permit.
     */
    public FacilityPermit() {

    }
}
