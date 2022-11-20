package com.creditsaisonassesment.creditsaisonmachinecoding.helper.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.creditsaisonassesment.creditsaisonmachinecoding.entities.FacilityPermit;
import com.creditsaisonassesment.creditsaisonmachinecoding.helper.CSVHelper;
import com.creditsaisonassesment.creditsaisonmachinecoding.helper.service.CSVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller class to upload a csv file to db
 */
@Controller
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    CSVService fileService;

    /**
     * Upload file response entity.
     *
     * @param multipartFile the multipart file
     * @return the response entity
     * @throws IOException the io exception
     */
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String message = "";
        File file = fileService.convertMultiPartToFile(multipartFile);
        if (CSVHelper.hasCSVFormat(multipartFile)) {
            try {
                String uri = fileService.uploadToDB(file);
                return ResponseEntity.status(HttpStatus.OK).body(""+message+" : "+uri);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getName() + "!\n";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message + e.getMessage());
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message+"");
    }
}