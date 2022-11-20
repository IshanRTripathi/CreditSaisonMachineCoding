package com.creditsaisonassesment.creditsaisonmachinecoding.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.creditsaisonassesment.creditsaisonmachinecoding.entities.FacilityPermit;
import com.creditsaisonassesment.creditsaisonmachinecoding.helper.service.CSVService;
import com.creditsaisonassesment.creditsaisonmachinecoding.repository.FacilityPermitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
class FacilityPermitServiceTest {

    @InjectMocks
    private FacilityPermitService facilityPermitService;

    @Mock
    FacilityPermitRepository facilityPermitRepository;

    @Mock
    CSVService csvService;

    @Mock
    ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(facilityPermitService, "facilityPermitRepository", facilityPermitRepository);
        ReflectionTestUtils.setField(facilityPermitService, "csvService", csvService);
        ReflectionTestUtils.setField(facilityPermitService, "objectMapper", objectMapper);
        when(facilityPermitRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(List.of(new FacilityPermit().setLocationId(123L))));
    }

    @Test
    void getFacilityPermitByLocationIdTest() {
        when(facilityPermitRepository.findById(any())).thenReturn(getFacilityPermit());
        assertNotNull(facilityPermitService.getFacilityPermitByLocationId("364218"));
    }

    private Optional<FacilityPermit> getFacilityPermit() {
        return Optional.of(new FacilityPermit().setLocationId(123L));
    }

    private Optional<List<FacilityPermit>> getFacilityPermits() {
        return Optional.of(List.of(new FacilityPermit().setLocationId(123L).setPermit("12MFF-0083").setLatitude("37.78788969990609").setLongitude("-122.40053532677749")));
    }

    @Test
    void getFacilityPermitByApplicantNameTest() {
        when(facilityPermitRepository.findAllByApplicant(any())).thenReturn(getFacilityPermits());
        facilityPermitService.getFacilityPermitByApplicantName(any());
    }

    @Test
    void getFacilityPermitByStreetNameTest() {
        when(facilityPermitRepository.findAllByLocationDescription(any())).thenReturn(getFacilityPermits());
        facilityPermitService.getFacilityPermitByStreetName(any());
    }

    @Test
    void createFacilityPermitTest() {
        when(facilityPermitRepository.save(any())).thenReturn(getFacilityPermit().get());
        facilityPermitService.createFacilityPermit(any());
    }

    @Test
    void getExpiredFacilityPermitsTest() {
        when(facilityPermitRepository.findAllByStatusAndExpirationDateLessThan(any(), any())).thenReturn(getFacilityPermits());
        when(facilityPermitRepository.findAllByStatusAndExpirationDateLessThanEqual(any(), any())).thenReturn(getFacilityPermits());
        facilityPermitService.getExpiredFacilityPermits(any());
    }

    @Test
    void getClosestTruckFromLocationTest() {
        when(facilityPermitRepository.findAll()).thenReturn(getFacilityPermits().get());
        facilityPermitService.getClosestTruckFromLocation("37.78788969990609", "-122.40053532677749");
    }

    @Test
    void getAllPermitsTest() {
        when(facilityPermitRepository.findAll()).thenReturn(getFacilityPermits().get());
        facilityPermitService.getAllPermits(1);
    }
}