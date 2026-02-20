package com.example.control.synapse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EventSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Seat seatId;
    private Boolean availability;

    @ManyToOne
    private Booking bookingId;
    private double price;

    @ManyToOne
    private Event eventId;
    public EventSeat() {}
    public EventSeat(Seat seatId, Boolean availability, Booking bookingId, double price, Event eventId) {
        this.seatId = seatId;
        this.availability = availability;
        this.bookingId = bookingId;
        this.price = price;
        this.eventId = eventId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Seat getSeatId() {
        return seatId;
    }
    public void setSeatId(Seat seatId) {
        this.seatId = seatId;
    }
    public Boolean getAvailability() {
        return availability;
    }
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    public Booking getBookingId() {
        return bookingId;
    }
    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Event getEventId() {
        return eventId;
    }
    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    

}
