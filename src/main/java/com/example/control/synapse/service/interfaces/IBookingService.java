package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.response.BookingResponseDto;

public interface IBookingService {
    Map<String, String> reserveSeat(List<Long> seatIdlist);

    Map<String, String> confirmBooking(List<Long> seatIdlist, Long userId, Long eventId, Long stadiumId);

    void shutdownScheduler();

    BookingResponseDto getBookingById(Long id);

    List<BookingResponseDto> getBookingByUserId(Long userId);

    List<BookingResponseDto> getAllBookings();

    List<BookingResponseDto> getBookingByEventId(Long eventId);

    List<BookingResponseDto> getBookingByStadiumId(Long stadiumId);
}
