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
    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seatId) {
        this.seat = seatId;
    }
    public Boolean getAvailability() {
        return availability;
    }
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking bookingId) {
        this.booking = bookingId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event eventId) {
        this.event = eventId;
    }

    

}
