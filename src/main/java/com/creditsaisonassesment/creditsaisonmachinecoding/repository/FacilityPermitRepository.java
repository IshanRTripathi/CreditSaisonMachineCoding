package com.creditsaisonassesment.creditsaisonmachinecoding.repository;

import java.util.List;
import java.util.Optional;

import com.creditsaisonassesment.creditsaisonmachinecoding.entities.FacilityPermit;
import com.creditsaisonassesment.creditsaisonmachinecoding.entities.enums.PermitStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Facility permit repository.
 */
@Repository
public interface FacilityPermitRepository extends JpaRepository<FacilityPermit, Long> {
    /**
     * Find all by applicant optional.
     *
     * @param applicantName the applicant name
     * @return the optional
     */
    Optional<List<FacilityPermit>> findAllByApplicant(String applicantName);

    Page<FacilityPermit> findAll(Pageable pageable);

    /**
     * Find all by location description optional.
     *
     * @param streetName the street name
     * @return the optional
     */
    Optional<List<FacilityPermit>> findAllByLocationDescription(String streetName);

    /**
     * Find all by status optional.
     *
     * @param expired the expired
     * @return the optional
     */
    Optional<List<FacilityPermit>> findAllByStatus(PermitStatus expired);

    /**
     * Find all by status and expiration date less than optional.
     *
     * @param status   the status
     * @param dateTime the date time
     * @return the optional
     */
    Optional<List<FacilityPermit>> findAllByStatusAndExpirationDateLessThan(PermitStatus status, String dateTime);

    /**
     * Find all by status and expiration date less than equal optional.
     *
     * @param status   the status
     * @param dateTime the date time
     * @return the optional
     */
    Optional<List<FacilityPermit>> findAllByStatusAndExpirationDateLessThanEqual(PermitStatus status, String dateTime);
}