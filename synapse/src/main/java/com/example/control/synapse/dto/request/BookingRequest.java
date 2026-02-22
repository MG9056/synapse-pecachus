package com.example.control.synapse.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class BookingRequest {

    
    private Long userId;
    private Long eventId;
    private List<Long> seatIdList;
    private Long stadiumId;
    private LocalDateTime bookingTime;

  
    public BookingRequest(){}
    public BookingRequest(Long userId, Long eventId, List<Long>seatIdlist, Long stadiumId, LocalDateTime bookingTime)
    {
        this.userId=userId;
        this.eventId=eventId;
        this.seatIdList=seatIdlist;
        this.bookingTime=bookingTime;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<Long> getSeatIdList(){
        return seatIdList;
    }

    public void setSeatIdList(List<Long> seatIdList)
    {
        this.seatIdList=seatIdList;
    }

      public Long getStadiumId() {
        return stadiumId;
    }
    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}
