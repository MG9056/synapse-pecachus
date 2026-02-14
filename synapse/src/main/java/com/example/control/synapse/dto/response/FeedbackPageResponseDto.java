package com.example.control.synapse.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class FeedbackPageResponseDto {
    private List<FeedbackResponseDto> content; // The actual list of events
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private Boolean last;
    
    public FeedbackPageResponseDto() {}

    public List<FeedbackResponseDto> getData() {
        return content;
    }

    public void setData(List<FeedbackResponseDto> data) {
        this.content = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }
    
}
