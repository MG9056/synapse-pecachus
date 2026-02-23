package com.example.control.synapse.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.control.synapse.dto.request.SeatDTO;

public interface ISeatService {
    SeatDTO createSeat(SeatDTO seatDTO);

    SeatDTO updateSeat(Long id, SeatDTO seatDTO);

    SeatDTO getSeatById(Long id);

    List<SeatDTO> getSeatsByStadiumId(Long stadiumId);

    List<SeatDTO> getAllSeats();

    void deleteSeat(Long id);

    List<SeatDTO> importSeatsFromCSV(MultipartFile file, Long stadiumId);
}
