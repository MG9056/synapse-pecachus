package com.example.control.synapse.dto.request;

import java.util.List;

public class BookingRequest {

    
    private Long userId;
    private Long eventId;
    private List<Long> seatIdList;

    public BookingRequest(){}
    public BookingRequest(Long userId, Long eventId, List<Long>seatIdlist)
    {
        this.userId=userId;
        this.eventId=eventId;
        this.seatIdList=seatIdlist;
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
}
