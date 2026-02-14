package com.example.control.synapse.dto.request;

import java.time.LocalDateTime;

public class EventRequestDto {
    private String name;
    private LocalDateTime dateTime;
    private String category;
    private Long stadiumId;
    private String description;
    private Double minPrice;

    public EventRequestDto() {}

    public EventRequestDto(String name, LocalDateTime dateTime, String category, Long stadiumId, String description,
            Double minPrice) {
        this.name = name;
        this.dateTime = dateTime;
        this.category = category;
        this.stadiumId = stadiumId;
        this.description = description;
        this.minPrice = minPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
    
}
