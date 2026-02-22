package com.example.control.synapse.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class EventPageResponseDto {
    private List<EventResponseDto> content; // The actual list of events
    private int pageNo;
    private int pageSize;
    private long totalElements; // Total events in DB (e.g., 50)
    private int totalPages;     // Total pages available (e.g., 5)
    private boolean last;
    public EventPageResponseDto() {}
    
    public EventPageResponseDto(List<EventResponseDto> content, int pageNo, int pageSize, long totalElements,
            int totalPages, boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
    
}
