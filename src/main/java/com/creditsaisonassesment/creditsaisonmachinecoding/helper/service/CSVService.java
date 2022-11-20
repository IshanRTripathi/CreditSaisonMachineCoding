package com.creditsaisonassesment.creditsaisonmachinecoding.helper.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import com.creditsaisonassesment.creditsaisonmachinecoding.helper.CSVHelper;
import com.creditsaisonassesment.creditsaisonmachinecoding.repository.FacilityPermitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * The type Csv service.
 */
@Service
public class CSVService {

    /**
     * The Repository.
     */
    @Autowired
    FacilityPermitRepository repository;

    /**
     * Gets list of permits from csv.
     *
     * @param file the file
     */
    public void saveListOfPermitsFromCSVToDB(File file) {
        try {
            repository.saveAll(CSVHelper.csvToData(new FileInputStream(file)));
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    /**
     * Convert multi part to file file.
     *
     * @param file the file
     * @return the file
     * @throws IOException the io exception
     */
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }

    public String uploadToDB(File file) {
        saveListOfPermitsFromCSVToDB(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/csv/download/")
            .path(file.getName())
            .toUriString();
        return fileDownloadUri;
    }
}
