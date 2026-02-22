package com.example.control.synapse.error;

public class SeatNotAvailableException extends RuntimeException {

    public SeatNotAvailableException(Long seatId) {
        super(String.format("Seat with id '%d' is not available", seatId));
    }

    public SeatNotAvailableException(String message) {
        super(message);
    }
}
