package com.creditsaisonassesment.creditsaisonmachinecoding.helper.common;

import java.io.File;

import javax.annotation.PostConstruct;

import com.creditsaisonassesment.creditsaisonmachinecoding.helper.service.CSVService;
import com.creditsaisonassesment.creditsaisonmachinecoding.repository.FacilityPermitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * The type Configurations.
 */
@Slf4j
@Component
public class Configurations {

    /**
     * Gets object mapper.
     *
     * @return the object mapper
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        log.info("Instantiating Objectmapper");
        return new ObjectMapper();
    }
}
