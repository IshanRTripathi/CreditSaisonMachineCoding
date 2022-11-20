package com.creditsaisonassesment.creditsaisonmachinecoding.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.creditsaisonassesment.creditsaisonmachinecoding.entities.FacilityPermit;
import com.creditsaisonassesment.creditsaisonmachinecoding.entities.enums.PermitStatus;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Csv helper.
 */
public class CSVHelper {
    /**
     * The constant CONTENT_TYPE.
     */
    public static String CONTENT_TYPE = "text/csv";

    /**
     * Has csv format boolean.
     *
     * @param file the file
     * @return the boolean
     */
    public static boolean hasCSVFormat(MultipartFile file) {
        return CONTENT_TYPE.equals(file.getContentType())
            || Objects.equals(file.getContentType(), "application/vnd.ms-excel");
    }

    /**
     * Csv to data list.
     *
     * @param is the is
     * @return the list
     */
    public static List<FacilityPermit> csvToData(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<FacilityPermit> facilityPermits = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                FacilityPermit facilityPermit = new FacilityPermit()
                    .setLocationId(Long.parseLong(csvRecord.get("locationid")))
                    .setApplicant(csvRecord.get("Applicant"))
                    .setFacilityType(csvRecord.get("FacilityType"))
                    .setCnn(csvRecord.get("cnn"))
                    .setLocationDescription(csvRecord.get("locationDescription"))
                    .setAddress(csvRecord.get("Address"))
                    .setBlockLot(csvRecord.get("blocklot"))
                    .setBlock(csvRecord.get("block"))
                    .setLot(csvRecord.get("lot"))
                    .setPermit(csvRecord.get("permit"))
                    .setStatus(PermitStatus.valueOf(csvRecord.get("Status")))
                    .setFoodItems(csvRecord.get("FoodItems"))
                    .setX(csvRecord.get("X"))
                    .setY(csvRecord.get("Y"))
                    .setLatitude(csvRecord.get("Latitude"))
                    .setLongitude(csvRecord.get("Longitude"))
                    .setSchedule(csvRecord.get("Schedule"))
                    .setDaysHours(csvRecord.get("dayshours"))
                    .setNOISent(csvRecord.get("NOISent"))
                    .setApproved(csvRecord.get("Approved"))
                    .setReceived(csvRecord.get("Received"))
                    .setPriorPermit(csvRecord.get("PriorPermit"))
                    .setExpirationDate(csvRecord.get("ExpirationDate"))
                    .setLocation(csvRecord.get("location"))
                    .setFirePreventionDistricts(csvRecord.get("Fire Prevention Districts"))
                    .setPoliceDistricts(csvRecord.get("Police Districts"))
                    .setSupervisorDistricts(csvRecord.get("Supervisor Districts"))
                    .setZipCodes(csvRecord.get("Zip Codes"))
                    .setNeighborhoods(csvRecord.get("Neighborhoods (old)"));
                facilityPermits.add(facilityPermit);
            }

            return facilityPermits;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    /**
     * Data to csv byte array input stream.
     *
     * @param facilityPermits the facility permits
     * @return the byte array input stream
     */
    public static ByteArrayInputStream dataToCSV(List<FacilityPermit> facilityPermits) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (FacilityPermit facilityPermit : facilityPermits) {
                List<String> data = Arrays.asList(
//                    String.valueOf(developerTutorial.getId()),
//                    developerTutorial.getTitle(),
//                    developerTutorial.getDescription(),
//                    String.valueOf(developerTutorial.isPublished())
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}