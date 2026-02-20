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
    private Seat seat;
    private Boolean availability;
    @ManyToOne
    private Booking booking;
    private double price;
    @ManyToOne
    private Event event;
    public EventSeat() {}
    public EventSeat(Seat seatId, Boolean availability, Booking bookingId, double price, Event eventId) {
        this.seat = seatId;
        this.availability = availability;
        this.booking = bookingId;
        this.price = price;
        this.event = eventId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Seat getSeatId() {
        return seat;
    }
    public void setSeatId(Seat seatId) {
        this.seat = seatId;
    }
    public Boolean getAvailability() {
        return availability;
    }
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    public Booking getBookingId() {
        return booking;
    }
    public void setBookingId(Booking bookingId) {
        this.booking = bookingId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Event getEventId() {
        return event;
    }
    public void setEventId(Event eventId) {
        this.event = eventId;
    }

    

}
