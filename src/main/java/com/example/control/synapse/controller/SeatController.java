package com.example.control.synapse.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.control.synapse.dto.request.SeatDTO;
import com.example.control.synapse.service.interfaces.ISeatService;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final ISeatService seatService;

    public SeatController(ISeatService seatService) {
        this.seatService = seatService;
    }

    /**
     * Create a single seat
     * POST /api/seats
     */
    @PostMapping
    public ResponseEntity<ApiResponse<SeatDTO>> createSeat(@Valid @RequestBody SeatDTO seatDTO) {
        SeatDTO createdSeat = seatService.createSeat(seatDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Seat created successfully", createdSeat));
    }

    /**
     * Import seats from CSV file
     * POST /api/seats/import?stadiumId={stadiumId}
     */
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<List<SeatDTO>>> importSeats(
            @RequestParam MultipartFile file,
            @RequestParam Long stadiumId) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Please upload a file", null));
        }

        List<SeatDTO> importedSeats = seatService.importSeatsFromCSV(file, stadiumId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true,
                        "Successfully imported " + importedSeats.size() + " seats",
                        importedSeats));
    }

    /**
     * Update an existing seat
     * PUT /api/seats/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SeatDTO>> updateSeat(
            @PathVariable Long id,
            @Valid @RequestBody SeatDTO seatDTO) {

        SeatDTO updatedSeat = seatService.updateSeat(id, seatDTO);

        return ResponseEntity.ok(new ApiResponse<>(true, "Seat updated successfully", updatedSeat));
    }

    /**
     * Get seat by ID
     * GET /api/seats/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SeatDTO>> getSeatById(@PathVariable Long id) {

        SeatDTO seat = seatService.getSeatById(id);

        return ResponseEntity.ok(new ApiResponse<>(true, "Seat retrieved successfully", seat));
    }

    /**
     * Get all seats by stadium ID
     * GET /api/seats/stadium/{stadiumId}
     */
    @GetMapping("/stadium/{stadiumId}")
    public ResponseEntity<ApiResponse<List<SeatDTO>>> getSeatsByStadiumId(
            @PathVariable Long stadiumId) {

        List<SeatDTO> seats = seatService.getSeatsByStadiumId(stadiumId);

        return ResponseEntity.ok(new ApiResponse<>(true,
                "Retrieved " + seats.size() + " seats", seats));
    }

    /**
     * Get all seats
     * GET /api/seats
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<SeatDTO>>> getAllSeats() {

        List<SeatDTO> seats = seatService.getAllSeats();

        return ResponseEntity.ok(new ApiResponse<>(true,
                "Retrieved " + seats.size() + " seats", seats));
    }

    /**
     * Delete seat by ID
     * DELETE /api/seats/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSeat(@PathVariable Long id) {

        seatService.deleteSeat(id);

        return ResponseEntity.ok(new ApiResponse<>(true, "Seat deleted successfully", null));
    }

    /**
     * API Response wrapper
     */
    @lombok.Data
    @lombok.AllArgsConstructor
    @lombok.NoArgsConstructor
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;
    }
}