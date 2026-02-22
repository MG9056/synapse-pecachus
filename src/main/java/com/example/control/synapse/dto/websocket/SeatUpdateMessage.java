package com.example.control.synapse.dto.websocket;

public class SeatUpdateMessage {

    private Long eventSeatId;   // ID of EventSeat record
    private Long eventId;       // Which event
    // private Long seatId;        // Physical seat ID
    private boolean available;  // true = free
    private String status;      // AVAILABLE / RESERVED / BOOKED
    private double price;       // optional but useful

    public SeatUpdateMessage() {}

    public SeatUpdateMessage(Long eventSeatId,
                             Long eventId,
                            //  Long seatId,
                             boolean available,
                             String status,
                             double price) {
        this.eventSeatId = eventSeatId;
        this.eventId = eventId;
        // this.seatId = seatId;
        this.available = available;
        this.status = status;
        this.price = price;
    }

    // getters + setters

    public Long getEventSeatId() { return eventSeatId; }
    public void setEventSeatId(Long eventSeatId) { this.eventSeatId = eventSeatId; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    // public Long getSeatId() { return seatId; }
    // public void setSeatId(Long seatId) { this.seatId = seatId; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
