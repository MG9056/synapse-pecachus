package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.BookingRequest;
import com.example.control.synapse.dto.response.BookingResponseDto;

public interface IBookingService {
    Map<String, String> bookTicket(BookingRequest bookingRequest);

    List<BookingResponseDto> getAllBookings();

    List<BookingResponseDto> getBookingByUserId(Long userId);

    List<BookingResponseDto> getBookingByEventId(Long eventId);

    List<BookingResponseDto> getBookingByStadiumId(Long stadiumId);

    BookingResponseDto getBookingById(Long id);

    Map<String, String> confirmTicket(Long bookingId);
}
