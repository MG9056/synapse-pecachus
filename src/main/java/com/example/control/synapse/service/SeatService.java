package com.example.control.synapse.service;

import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.request.SeatDTO;
import com.example.control.synapse.helper.CsvHelper;
import com.example.control.synapse.models.Seat;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.SeatRepository;
import com.example.control.synapse.repository.StadiumRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.example.control.synapse.service.interfaces.ISeatService;

@Service
@Slf4j
public class SeatService implements ISeatService {

    private final SeatRepository seatRepository;
    private final StadiumRepository stadiumRepository;

    public SeatService(SeatRepository seatRepository, StadiumRepository stadiumRepository) {
        this.seatRepository = seatRepository;
        this.stadiumRepository = stadiumRepository;
    }

    @Transactional
    public SeatDTO createSeat(SeatDTO seatDTO) {
        log.info("Creating seat: {}", seatDTO);

        // Check if stadium exists
        Stadium stadium = stadiumRepository.findById(seatDTO.getStadiumId())
                .orElseThrow(() -> new RuntimeException(
                        "Stadium not found with id: " + seatDTO.getStadiumId()));

        // Check if seat already exists
        if (seatRepository.existsByRowAndSeatNoAndStadiumId(
                seatDTO.getRow(), seatDTO.getSeatNo(), seatDTO.getStadiumId())) {
            throw new RuntimeException("Seat already exists in row " + seatDTO.getRow() +
                    " with seat number " + seatDTO.getSeatNo());
        }

        Seat seat = mapToEntity(seatDTO, stadium);
        Seat savedSeat = seatRepository.save(seat);

        log.info("Seat created successfully with id: {}", savedSeat.getId());
        return mapToDTO(savedSeat);
    }

    @Transactional
    public SeatDTO updateSeat(Long id, SeatDTO seatDTO) {
        log.info("Updating seat with id: {}", id);

        Seat existingSeat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        // Check if stadium exists if stadium ID is being changed
        Stadium stadium = existingSeat.getStadium();
        if (!existingSeat.getStadium().getId().equals(seatDTO.getStadiumId())) {
            stadium = stadiumRepository.findById(seatDTO.getStadiumId())
                    .orElseThrow(() -> new RuntimeException(
                            "Stadium not found with id: " + seatDTO.getStadiumId()));
        }

        // Update fields
        existingSeat.setRow(seatDTO.getRow());
        existingSeat.setSeatNo(seatDTO.getSeatNo());
        existingSeat.setCloseToWc(seatDTO.getCloseToWc());
        existingSeat.setCloseToFoodStall(seatDTO.getCloseToFoodStall());
        existingSeat.setCloseToExit(seatDTO.getCloseToExit());
        existingSeat.setIsWomen(seatDTO.getIsWomen());
        existingSeat.setIsAccessible(seatDTO.getIsAccessible());
        existingSeat.setCategory(seatDTO.getCategory());
        existingSeat.setStadium(stadium);

        Seat updatedSeat = seatRepository.save(existingSeat);

        log.info("Seat updated successfully with id: {}", updatedSeat.getId());
        return mapToDTO(updatedSeat);
    }

    @Transactional(readOnly = true)
    public SeatDTO getSeatById(Long id) {
        log.info("Fetching seat with id: {}", id);

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        return mapToDTO(seat);
    }

    @Transactional(readOnly = true)
    public List<SeatDTO> getSeatsByStadiumId(Long stadiumId) {
        log.info("Fetching seats for stadium id: {}", stadiumId);

        // Check if stadium exists
        if (!stadiumRepository.existsById(stadiumId)) {
            throw new RuntimeException("Stadium not found with id: " + stadiumId);
        }

        List<Seat> seats = seatRepository.findByStadiumId(stadiumId);

        log.info("Found {} seats for stadium id: {}", seats.size(), stadiumId);
        return seats.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SeatDTO> getAllSeats() {
        log.info("Fetching all seats");

        List<Seat> seats = seatRepository.findAll();

        log.info("Found {} seats", seats.size());
        return seats.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSeat(Long id) {
        log.info("Deleting seat with id: {}", id);

        if (!seatRepository.existsById(id)) {
            throw new RuntimeException("Seat not found with id: " + id);
        }

        seatRepository.deleteById(id);
        log.info("Seat deleted successfully with id: {}", id);
    }

    @Transactional
    public List<SeatDTO> importSeatsFromCSV(MultipartFile file, Long stadiumId) {
        log.info("Importing seats from CSV for stadium id: {}", stadiumId);

        // Validate file
        if (!CsvHelper.hasCSVFormat(file)) {
            throw new RuntimeException("Please upload a CSV file!");
        }

        // Check if stadium exists
        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new RuntimeException(
                        "Stadium not found with id: " + stadiumId));

        try {
            // Parse CSV
            List<SeatDTO> seatDTOs = CsvHelper.csvToSeats(file, stadiumId);

            // Convert to entities and save
            List<Seat> seatsToSave = new ArrayList<>();
            int skipCount = 0;

            for (SeatDTO dto : seatDTOs) {
                // Manual validation
                if (dto.getRow() == null || dto.getRow().trim().isEmpty()) {
                    throw new RuntimeException("Validation Error: Row is required for all seats");
                }
                if (dto.getSeatNo() == null) {
                    throw new RuntimeException("Validation Error: Seat number is required for all seats");
                }
                if (dto.getCategory() == null || dto.getCategory().trim().isEmpty()) {
                    throw new RuntimeException("Validation Error: Category is required for all seats");
                }

                // Check for existing seat to avoid duplicates
                if (seatRepository.existsByRowAndSeatNoAndStadiumId(
                        dto.getRow(), dto.getSeatNo(), stadiumId)) {
                    skipCount++;
                    continue;
                }

                seatsToSave.add(mapToEntity(dto, stadium));
            }

            if (seatsToSave.isEmpty() && skipCount > 0) {
                throw new RuntimeException("All seats in CSV already exist in the database.");
            }

            List<Seat> savedSeats = seatRepository.saveAll(seatsToSave);

            log.info("Successfully imported {} seats, skipped {} duplicates",
                    savedSeats.size(), skipCount);

            return savedSeats.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error importing seats from CSV", e);
            throw new RuntimeException("Failed to import seats: " + e.getMessage(), e);
        }
    }

    /**
     * Map entity to DTO
     */
    private SeatDTO mapToDTO(Seat seat) {
        SeatDTO dto = new SeatDTO();
        dto.setId(seat.getId());
        dto.setRow(seat.getRow());
        dto.setSeatNo(seat.getSeatNo());
        dto.setCloseToWc(seat.getCloseToWc());
        dto.setCloseToFoodStall(seat.getCloseToFoodStall());
        dto.setCloseToExit(seat.getCloseToExit());
        dto.setIsWomen(seat.getIsWomen());
        dto.setIsAccessible(seat.getIsAccessible());
        dto.setCategory(seat.getCategory());
        dto.setStadiumId(seat.getStadium().getId());
        return dto;
    }

    /**
     * Map DTO to entity
     */
    private Seat mapToEntity(SeatDTO dto, Stadium stadium) {
        Seat seat = new Seat();
        seat.setRow(dto.getRow());
        seat.setSeatNo(dto.getSeatNo());
        seat.setCloseToWc(dto.getCloseToWc() != null ? dto.getCloseToWc() : false);
        seat.setCloseToFoodStall(dto.getCloseToFoodStall() != null ? dto.getCloseToFoodStall() : false);
        seat.setCloseToExit(dto.getCloseToExit() != null ? dto.getCloseToExit() : false);
        seat.setIsWomen(dto.getIsWomen() != null ? dto.getIsWomen() : false);
        seat.setIsAccessible(dto.getIsAccessible() != null ? dto.getIsAccessible() : false);
        seat.setCategory(dto.getCategory());
        seat.setStadium(stadium);
        return seat;
    }
}
