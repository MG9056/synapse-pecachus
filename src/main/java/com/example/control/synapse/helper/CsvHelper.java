package com.example.control.synapse.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.control.synapse.dto.request.SeatDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
    
    public static String TYPE = "text/csv";
    
    /**
     * Check if file is CSV
     */
    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType()) || 
               file.getOriginalFilename() != null && 
               file.getOriginalFilename().endsWith(".csv");
    }
    
    /**
     * Parse CSV file to list of SeatDTO
     * Expected CSV format:
     * row,seatNo,closeToWc,closeToFoodStall,closeToExit,isWomen,isAccessible,category,stadiumId
     */
    public static List<SeatDTO> csvToSeats(MultipartFile file, Long defaultStadiumId) {
        try (BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.builder()
                             .setHeader()
                             .setSkipHeaderRecord(true)
                             .setIgnoreHeaderCase(true)
                             .setTrim(true)
                             .build())) {
            
            List<SeatDTO> seats = new ArrayList<>();
            
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            
            for (CSVRecord csvRecord : csvRecords) {
                SeatDTO seat = new SeatDTO();
                
                seat.setRow(csvRecord.get("row"));
                seat.setSeatNo(Integer.parseInt(csvRecord.get("seatNo")));
                
                // Parse boolean fields with null safety
                seat.setCloseToWc(parseBoolean(csvRecord.get("closeToWc")));
                seat.setCloseToFoodStall(parseBoolean(csvRecord.get("closeToFoodStall")));
                seat.setCloseToExit(parseBoolean(csvRecord.get("closeToExit")));
                seat.setIsWomen(parseBoolean(csvRecord.get("isWomen")));
                seat.setIsAccessible(parseBoolean(csvRecord.get("isAccessible")));
                
                seat.setCategory(csvRecord.get("category"));
                
                // Use stadiumId from CSV if present, otherwise use default
                String stadiumIdStr = csvRecord.get("stadiumId");
                if (stadiumIdStr != null && !stadiumIdStr.isEmpty()) {
                    seat.setStadiumId(Long.parseLong(stadiumIdStr));
                } else {
                    seat.setStadiumId(defaultStadiumId);
                }
                
                seats.add(seat);
            }
            
            return seats;
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage(), e);
        }
    }
    
    /**
     * Parse boolean from string with null safety
     */
    private static Boolean parseBoolean(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        return Boolean.parseBoolean(value);
    }
}