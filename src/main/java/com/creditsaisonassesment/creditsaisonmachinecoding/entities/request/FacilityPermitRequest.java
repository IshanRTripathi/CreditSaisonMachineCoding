package com.creditsaisonassesment.creditsaisonmachinecoding.entities.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityPermitRequest {

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
    private String status;
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
}
